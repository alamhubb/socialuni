package com.socialuni.social.common.sdk.event.ddd;

import com.socialuni.social.common.api.model.PublishDataModel;
import com.socialuni.social.common.sdk.component.SocialuniPublishDataComponent;

/**
 * @see PublishDataModel
 * @see SocialuniPublishDataComponent
 */
public abstract class AbstractPublishDataModelConsum implements EventConsum{
    @Override
    public boolean canConsum(String topicName, Object event) {
        return event instanceof PublishDataModel && canConsum((PublishDataModel)event);
    }

    @Override
    public void consumEvent(Object event) {
        if(event instanceof PublishDataModel){
            PublishDataModel publishDataModel = (PublishDataModel) event;
            this.consumEvent(publishDataModel);
        }
    }

    public  boolean canConsum(PublishDataModel publishDataModel){
        return true;
    }

    public abstract void consumEvent(PublishDataModel publishDataModel);
}
