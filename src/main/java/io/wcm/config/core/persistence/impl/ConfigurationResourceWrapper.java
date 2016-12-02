/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2016 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.config.core.persistence.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceWrapper;
import org.apache.sling.api.resource.ValueMap;

/**
 * Wrapper that returns an enhanced value map for the resource
 * providing a merged map with all inherited property values.
 */
final class ConfigurationResourceWrapper extends ResourceWrapper {

  private final ValueMap props;

  ConfigurationResourceWrapper(Resource resource, ValueMap props) {
    super(unwrap(resource));
    this.props = props;
  }

  private static Resource unwrap(Resource resource) {
    if (resource instanceof ConfigurationResourceWrapper) {
      return ((ConfigurationResourceWrapper)resource).getResource();
    }
    else {
      return resource;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public <AdapterType> AdapterType adaptTo(Class<AdapterType> type) {
    if (type == ValueMap.class) {
      return (AdapterType)props;
    }
    return super.adaptTo(type);
  }

  @Override
  public ValueMap getValueMap() {
    return props;
  }

}
