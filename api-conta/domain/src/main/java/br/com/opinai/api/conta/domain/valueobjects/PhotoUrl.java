package br.com.opinai.api.conta.domain.valueobjects;

import com.opinai.shared.domain.ValueObject;
import com.opinai.shared.domain.validation.Error;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.regex.Pattern;

public class PhotoUrl extends ValueObject<String> {
    private static final Set<String> ALLOWED_IMAGE_EXTENSIONS = Set.of("jpg", "jpeg", "png");
    private static final Pattern EXTENSION_PATTERN = Pattern.compile(".*\\.(jpg|jpeg|png)$", Pattern.CASE_INSENSITIVE);

    private final String value;

    private PhotoUrl(String value) {
        this.value = value;
        selfValidate();
    }

    public void selfValidate() {
        if (value == null || value.isBlank()) {
            this.notification.append(new Error("URL não ser nula ou vazia."));
        }

        try {
            URL parsedUrl = new URL(value);
            if (!"https".equalsIgnoreCase(parsedUrl.getProtocol())
                    || !EXTENSION_PATTERN.matcher(parsedUrl.getPath()).matches()) {
                this.notification.append(new Error("URL inválida."));
            }
        } catch (MalformedURLException e) {
            this.notification.append(new Error("URL inválida."));
        }
    }

    public static PhotoUrl from(String value) {
        return new PhotoUrl(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
