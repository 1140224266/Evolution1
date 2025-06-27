package evl.razier.Attribute;

import java.util.HashSet;
import java.util.Set;

public enum damageType {
    FORCE((HashSet<String>) Set.of("Force")),
    TEMPERATURE((HashSet<String>) Set.of("Temperature")),
    LIGHT((HashSet<String>) Set.of("Light")),
    SOUND((HashSet<String>) Set.of("Sound")),
    CHEMICAL((HashSet<String>) Set.of("Chemical"));
    public HashSet<String> description ;

    damageType(HashSet<String> description){
        this.description = description;
    }
}
