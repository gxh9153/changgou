keytool 命令 在jdk中运行
//创建证书
keytool -genkeypair -alias gxhlisa -keyalg RSA -keypass gxhlisa -keystore D:/jks/gxhlisa.jks -storepass gxhlisa
//安装openssl 并配置环境

导出公钥
keytool -list -rfc --keystore D:/jks/gxhlisa.jks | openssl x509 -inform pem -pubkey