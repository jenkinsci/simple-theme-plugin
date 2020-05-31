# Built-in theme developer guide

Built-in themes are discovered automatically by Jenkins if you extend from the [ThemeManagerFactory](/src/main/java/org/jenkinsci/plugins/thememanager/ThemeManagerFactory.java).

See the reference implementation in the [dark-theme](https://github.com/jenkinsci/dark-theme) for a working sample.

You should load your plugins theme from inside of your plugin, this means that versioning of your theme
will be automatically handled by plugin updates.

Sample code:

```java
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.jenkinsci.plugins.thememanager.Theme;
import org.jenkinsci.plugins.thememanager.ThemeManagerFactory;
import org.jenkinsci.plugins.thememanager.ThemeManagerFactoryDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebMethod;

public class YourThemeManagerFactory extends ThemeManagerFactory {

    @DataBoundConstructor
    public YourThemeManagerFactory() {
    }

    @Override
    public Theme getTheme() {
        return Theme.builder()
                .withCssUrl(getCssUrl())
                .build();
    }

    @Extension
    public static class YourThemeManagerFactoryDescriptor extends ThemeManagerFactoryDescriptor {

        @WebMethod(name = "theme.css")
        public void doDarkThemeCss(StaplerRequest req, StaplerResponse res) throws IOException {
            try (InputStream themeInputStream = getClass().getResourceAsStream("theme.css")) {
                res.setContentType("text/css");
                Objects.requireNonNull(themeInputStream);
                String s1 = IOUtils.toString(themeInputStream, StandardCharsets.UTF_8);
                res.getWriter().print(s1);
            }
        }

        @NonNull
        @Override
        public String getDisplayName() {
            return "Your theme"; // This is what shows up in on the user interface
        }

        @Override
        public ThemeManagerFactory getInstance() {
            return new YourThemeManagerFactory();
        }
    }
}
```


