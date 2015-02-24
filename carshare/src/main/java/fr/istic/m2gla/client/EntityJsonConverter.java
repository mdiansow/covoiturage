package fr.istic.m2gla.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import fr.istic.m2gla.shared.EntityFactory;
import fr.istic.m2gla.shared.IPerson;

public class EntityJsonConverter {

    private EntityJsonConverter() {
    }

    private static EntityJsonConverter instance = new EntityJsonConverter();


    // Instantiate the factory
    EntityFactory factory = GWT.create(EntityFactory.class);
    // In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);

    public IPerson makeUser() {
        // Construct the AutoBean
        AutoBean<IPerson> user = factory.user();

        // Return the Book interface shim
        return user.as();
    }

    String serializeToJson(IPerson user) {
        // Retrieve the AutoBean controller
        AutoBean<IPerson> bean = AutoBeanUtils.getAutoBean(user);

        return AutoBeanCodex.encode(bean).getPayload();
    }

    IPerson deserializeFromJson(String json) {
        AutoBean<IPerson> bean = AutoBeanCodex.decode(factory, IPerson.class, json);
        return bean.as();
    }

    public static EntityJsonConverter getInstance() {
        return instance;
    }
}
