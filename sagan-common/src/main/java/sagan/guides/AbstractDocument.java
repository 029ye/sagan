package sagan.guides;

import lombok.Data;
import lombok.Getter;

import org.springframework.util.Assert;

import static lombok.AccessLevel.NONE;

@Data
class AbstractDocument implements Document {

    private final @Getter(NONE) ContentProvider contentProvider;
    private String content;
    private String sidebar;

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
