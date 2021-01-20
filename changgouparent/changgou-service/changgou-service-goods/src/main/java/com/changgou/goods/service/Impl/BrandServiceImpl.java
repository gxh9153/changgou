package com.changgou.goods.service.Impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryBrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有品牌
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }

    /**
     * 根据id查询品牌
     *
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    /**
     * 添加品牌
     *
     * @param brand
     */
    @Override
    public int createBrand(Brand brand) {
        int count = brandMapper.insertSelective(brand);
        return count;
    }

    /**
     * 根据id修改品牌
     *
     * @param brand
     * @return
     */
    @Override
    public int updateBrand(Brand brand) {
        int count = brandMapper.updateByPrimaryKeySelective(brand);
        return count;
    }

    /**
     * 根据id删除品牌
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        int count = brandMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据条件查询品牌
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findByKeyWords(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询品牌
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<Brand> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return brandMapper.selectAll();
    }

    /**
     * 根据条件分页查询品牌
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findByKwAndPage(Integer pageNum, Integer pageSize, Brand brand) {
        PageHelper.startPage(pageNum,pageSize);

        Example example = createExample(brand);
        List<Brand> brandList = brandMapper.selectByExample(example);

        return brandList;
    }

    @Override
    public List<Brand> findByCategory(Integer categoryId) {
        List<Brand> list = brandMapper.findByCategory(categoryId);
        return list;
    }


    /**
     * 构建查询对象
     *
     * @param brand
     * @return
     */
    public Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            // 品牌名称
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            // 品牌图片地址
            if (!StringUtils.isEmpty(brand.getImage())) {
                criteria.andLike("image", "%" + brand.getImage() + "%");
            }
            // 品牌的首字母
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andLike("letter", "%" + brand.getLetter() + "%");
            }
            // 品牌id
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("id", brand.getId());
            }
            // 排序
            if (!StringUtils.isEmpty(brand.getSeq())) {
                criteria.andEqualTo("seq", brand.getSeq());
            }
        }
        return example;
    }
}