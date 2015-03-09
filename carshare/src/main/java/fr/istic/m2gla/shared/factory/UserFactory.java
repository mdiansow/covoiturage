package fr.istic.m2gla.shared.factory;


import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import fr.istic.m2gla.shared.IPerson;

public interface UserFactory extends AutoBeanFactory {
    AutoBean<IPerson> user();

//    AutoBean<IEvent> event();
//
//    AutoBean<IAvis> avis();
//
//    AutoBean<IPreference> pref();
//
//    AutoBean<IVille> ville();
}
