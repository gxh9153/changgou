package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FastDFSClient {

    /**
     * 初始化tracker信息
     */
    static{
        try{
            //获取tracker的配置文件fdfs_client.conf的位置
            String filePath = new ClassPathResource("fdfs_client.conf").getPath();
            //加载tracker的配置信息
            ClientGlobal.init(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 文件上传
     * @param file
     * @return
     *         1:文件上传所存储的组名
     *         2：文件存储路径
     */
    public static String[] upload (FastDFSFile file){
        //获取文件上传作者
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair(file.getAuthor());
        /***
         * 文件上传后的返回值
         * uploadResults[0]:文件上传所存储的组名，例如:group1
         * uploadResults[1]:文件存储路径,例如：M00/00/00/wKjThF0DBzaAP23MAAXz2mMp9oM26.jpeg
         */
        String[] uploadResults = null;
        try{
            //获取storageClient对象
            StorageClient storageClient = getStorageClient();
            //执行文件上传
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return uploadResults;
    }


    /**
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFile(String groupName,String remoteFileName){
        try{
            // 获取storageClient
            StorageClient storageClient = getStorageClient();
            //获取文件信息
            return storageClient.get_file_info(groupName,remoteFileName);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载文件
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName,String remoteFileName){
        try{
            // 获取storageClient
            StorageClient storageClient = getStorageClient();
            //下载文件
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            return new ByteArrayInputStream(bytes);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     */
    public static void deleteFile(String groupName,String remoteFileName){
        try{
            // 获取storageClient
            StorageClient storageClient = getStorageClient();
            //删除文件
            storageClient.delete_file(groupName,remoteFileName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取组的信息
     * @param groupName
     * @return
     */
    public static StorageServer getStorage(String groupName){
        try{
            //创建trackClient
            TrackerClient trackerClient = new TrackerClient();
            //获取trackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取组的信息
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer, groupName);
            return storeStorage;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据文件组名和文件服务器存储路径获取Storage服务ip,端口信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static StorageServer getServerInfo(String groupName, String remoteFileName){
        try{
            //创建trackClient
            TrackerClient trackerClient = new TrackerClient();
            //获取trackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //根据文件组名和文件服务器存储路径获取Storage服务ip,端口信息
            StorageServer fetchStorage = trackerClient.getFetchStorage(trackerServer, groupName, remoteFileName);
            return fetchStorage;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取tracker服务器地址
     * @return
     */
    public static String getTrackerUrl(){
        try{
            //获取trackerServer
            TrackerServer trackerServer = getTrackerServer();
            //获取trackerUrl
            return "http://"+trackerServer.getInetSocketAddress().getHostName()+":"+ClientGlobal.getG_tracker_http_port();

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    //获取trackerServer
    public static TrackerServer getTrackerServer() throws Exception{
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    //获取storageClient
    public static StorageClient getStorageClient() throws Exception{
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过trackerServer 获取StroageClient
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }
}
