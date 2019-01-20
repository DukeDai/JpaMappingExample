package com.dukedai.jpamapping;

import com.dukedai.jpamapping.domain.entity.*;
import com.dukedai.jpamapping.domain.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpamappingApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    // SQLs indicate JPA inserts rows one by one and step by step, depth-first.
    @Commit
    @Test
    public void testCascadingSave() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(System.currentTimeMillis() + "");

        productEntity.setItemList(produceItemList(productEntity));
        productEntity.setPropertyList(producePropertyEntityList(productEntity));

        productRepository.save(productEntity);
    }

    // SQLs indicate JPA retrieve rows step by step, depth-first.
    @Transactional
    @Test
    public void testCascadingLoad() {
        Optional<ProductEntity> productHolder = productRepository.findById(1L);
        if (productHolder.isPresent()) {
            ProductEntity product = productHolder.get();
            Assert.assertTrue(product.getItemList().size() == 9);
            Assert.assertTrue(product.getItemList().get(0).getAttributeList().size() == 2);

            Assert.assertTrue(product.getPropertyList().size() == 8);
            Assert.assertTrue(product.getPropertyList().get(1).getValueList().size() == 2);
        }
    }

    // retrieve by depth-first, then create new and update changed nodes.
    @Transactional
    @Test
    @Commit
    public void testCascadingUpdate() {
        Optional<ProductEntity> productHolder = productRepository.findById(1L);
        if (productHolder.isPresent()) {
            ProductEntity product = productHolder.get();
            ProductItemEntity item1 = product.getItemList().get(0);
            item1.setName("updated " + System.currentTimeMillis());

            ProductItemEntity item3 = produceItem(product, "item 3");
            product.getItemList().add(item3);// new insert row got updated again without change, not knowing why.

            product.getPropertyList().get(0).getValueList().get(0).setValue("updated value");

            ProductPropertyEntity prop3 = producePropertyEntity(product, "prop 3");
            product.getPropertyList().add(prop3);

            product = productRepository.save(product);

            // the size is changing.
            Assert.assertTrue(product.getItemList().size() + "", product.getItemList().size() == 9);
            Assert.assertTrue(product.getItemList().get(2).getAttributeList().size() == 2);

            Assert.assertTrue(product.getPropertyList().size() == 8);
            Assert.assertTrue(product.getPropertyList().get(1).getValueList().size() == 2);
            Assert.assertTrue("updated value".equals(product.getPropertyList().get(0).getValueList().get(0).getValue()));
        }
    }

    private List<ProductItemEntity> produceItemList(ProductEntity product) {
        List<ProductItemEntity> itemList = new ArrayList<>();
        itemList.add(produceItem(product, "item 1"));
        itemList.add(produceItem(product, "item 2"));
        return itemList;
    }

    private ProductItemEntity produceItem(ProductEntity product, String itemName) {
        ProductItemEntity item = new ProductItemEntity();
        item.setName(product.getName() + " " + itemName);
        item.setProduct(product);
        item.setAttributeList(produceItemAttributeList(item));
        return item;
    }

    private List<ProductItemAttributeEntity> produceItemAttributeList(ProductItemEntity item) {
        List<ProductItemAttributeEntity> itemAttrList = new ArrayList<>();
        ProductItemAttributeEntity attr1 = new ProductItemAttributeEntity();
        attr1.setName(item.getName() + " attr name 1");
        attr1.setValue(item.getName() + " attr value 1");
        attr1.setProductItem(item);
        itemAttrList.add(attr1);
        ProductItemAttributeEntity attr2 = new ProductItemAttributeEntity();
        attr2.setName(item.getName() + " attr name 2");
        attr2.setValue(item.getName() + " attr value 2");
        attr2.setProductItem(item);
        itemAttrList.add(attr2);
        return itemAttrList;
    }

    private List<ProductPropertyEntity> producePropertyEntityList(ProductEntity product) {
        List<ProductPropertyEntity> itemList = new ArrayList<>();
        itemList.add(producePropertyEntity(product, "prop 1"));
        itemList.add(producePropertyEntity(product, "prop 2"));
        return itemList;
    }

    private ProductPropertyEntity producePropertyEntity(ProductEntity product, String propertyName) {
        ProductPropertyEntity property = new ProductPropertyEntity();
        property.setName(product.getName() + " " + propertyName);
        property.setProduct(product);
        property.setValueList(producePropertyValueList(property));
        return property;
    }

    private List<ProductPropertyValueEntity> producePropertyValueList(ProductPropertyEntity property) {
        List<ProductPropertyValueEntity> valueList = new ArrayList<>();
        ProductPropertyValueEntity value1 = new ProductPropertyValueEntity();
        value1.setValue(property.getName() + " value 1 ");
        value1.setProductProperty(property);
        valueList.add(value1);
        ProductPropertyValueEntity value2 = new ProductPropertyValueEntity();
        value2.setValue(property.getName() + " value 2");
        value2.setProductProperty(property);
        valueList.add(value2);
        return valueList;
    }


}

