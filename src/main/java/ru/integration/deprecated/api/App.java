package ru.integration.deprecated.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ru.integration.deprecated.api.policlinicCase.Policlinic;
import ru.integration.deprecated.api.schedule.CalendarApi;
import ru.integration.deprecated.api.vocsync.VocOperations;
import ru.integration.deprecated.api.vocsync.VocSync;


@ApplicationPath("/api")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        //h.add(Auth.class);
        h.add(Person.class);
        h.add(VocSync.class);
        h.add(VocOperations.class);
        h.add(CalendarApi.class);
        h.add(Policlinic.class);

        return h;
    }
}
