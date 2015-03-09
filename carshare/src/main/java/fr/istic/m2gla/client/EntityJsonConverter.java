package fr.istic.m2gla.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import fr.istic.m2gla.shared.IEvent;
import fr.istic.m2gla.shared.IPerson;
import fr.istic.m2gla.shared.factory.UserFactory;

public class EntityJsonConverter {

    private EntityJsonConverter() {
    }

    private static EntityJsonConverter instance = new EntityJsonConverter();


    // Instantiate the factory
    UserFactory factory = GWT.create(UserFactory.class);
    // In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);

    //IEventFactory eventFactory = GWT.create(IEventFactory.class);

    public IPerson makeUser() {
        // Construct the AutoBean
        AutoBean<IPerson> user = factory.user();

        // Return the Person interface shim
        return user.as();
    }

//    public IEvent makeEvent(){
//        // Construct the AutoBean
//        AutoBean<IEvent> event = eventFactory.event();
//
//        // Return the event interface shim
//        return event.as();
//    }

    // Person
    String serializeUserToJson(IPerson user) {
        // Retrieve the AutoBean controller
        AutoBean<IPerson> bean = AutoBeanUtils.getAutoBean(user);

        return AutoBeanCodex.encode(bean).getPayload();
    }

    IPerson deserializeUserFromJson(String json) {
        AutoBean<IPerson> bean = AutoBeanCodex.decode(factory, IPerson.class, json);
        return bean.as();
    }

    // Event
    String serializeEventToJson(IEvent event) {
        // Retrieve the AutoBean controller
        AutoBean<IEvent> bean = AutoBeanUtils.getAutoBean(event);

        return AutoBeanCodex.encode(bean).getPayload();
    }

//    IEvent deserializeEventFromJson(String json) {
//        AutoBean<IEvent> bean = AutoBeanCodex.decode(eventFactory, IEvent.class, json);
//        return bean.as();
//    }

    public static EntityJsonConverter getInstance() {
        return instance;
    }
}
