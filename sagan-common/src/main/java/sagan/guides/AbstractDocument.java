package sagan.guides;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.util.Assert;

@RequiredArgsConstructor
class AbstractDocument implements Document {

    private final ContentProvider contentProvider;
    @Setter private String content;
    @Setter private String sidebar;

    public String getContent() {
        if (content == null) {
            contentProvider.populate(this);
        }
        // body must have at least some text to be valid
        Assert.hasText(content, "Expected body content to be populated");
        return content;
    }

    public String getSidebar() {
        if (sidebar == null) {
            contentProvider.populate(this);
        }
        // sidebar can be empty string but cannot be null
        Assert.notNull(sidebar, "Expected sidebar content to be populated");
        return sidebar;
    }

}
