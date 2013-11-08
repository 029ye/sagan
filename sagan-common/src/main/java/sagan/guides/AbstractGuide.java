package sagan.guides;

import lombok.Data;
import lombok.Delegate;

@Data
class AbstractGuide extends AbstractDocument implements Guide {

    @Delegate
    private final GuideMetadata metadata;
    private final ImageProvider imageProvider;

    public AbstractGuide(GuideMetadata metadata, ContentProvider contentProvider, ImageProvider imageProvider) {
        super(contentProvider);
        this.metadata = metadata;
        this.imageProvider = imageProvider;
    }

    @Override
    public byte[] getImage(String name) {
        return imageProvider.loadImage(this, name);
    }

}
