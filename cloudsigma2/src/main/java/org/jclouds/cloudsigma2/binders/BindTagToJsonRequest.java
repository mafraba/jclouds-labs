/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.cloudsigma2.binders;

import com.google.gson.JsonObject;
import org.jclouds.cloudsigma2.domain.Tag;
import org.jclouds.cloudsigma2.functions.TagToJson;
import org.jclouds.http.HttpRequest;
import org.jclouds.rest.Binder;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class BindTagToJsonRequest implements Binder {
   private final TagToJson createTagRequestToJson;

   @Inject
   public BindTagToJsonRequest(TagToJson createTagRequestToJson) {
      this.createTagRequestToJson = createTagRequestToJson;
   }

   @Override
   public <R extends HttpRequest> R bindToRequest(R request, Object input) {
      checkArgument(input instanceof Tag, "this binder is only valid for Tag!");
      Tag create = Tag.class.cast(input);

      JsonObject serverInfoJson = createTagRequestToJson.apply(create);
      request.setPayload(serverInfoJson.toString());
      request.getPayload().getContentMetadata().setContentType(MediaType.APPLICATION_JSON);
      return request;
   }
}

