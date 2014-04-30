
public abstract class Component implements ImageObserver, MenuContainer,
                                           Serializable
{

    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.Component");
    private static final PlatformLogger eventLog = PlatformLogger.getLogger("java.awt.event.Component");
    private static final PlatformLogger focusLog = PlatformLogger.getLogger("java.awt.focus.Component");
    private static final PlatformLogger mixingLog = PlatformLogger.getLogger("java.awt.mixing.Component");

    