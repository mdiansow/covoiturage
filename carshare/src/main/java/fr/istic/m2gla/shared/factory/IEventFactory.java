package fr.istic.m2gla.shared.factory;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import fr.istic.m2gla.shared.IEvent;

/**
 * Created by mds on 09/03/15.
 * Class ${CLASS}
 */
public interface IEventFactory extends AutoBeanFactory {
        AutoBean<IEvent> event();
}