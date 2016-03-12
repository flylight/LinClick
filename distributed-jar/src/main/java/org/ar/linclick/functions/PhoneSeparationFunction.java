package org.ar.linclick.functions;

import org.apache.spark.api.java.function.Function;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.utils.UserAgentUtil;

/**
 * Function that filter out results and leave only results from phone
 */
public class PhoneSeparationFunction implements Function<ClientInfo, Boolean> {
  @Override public Boolean call(ClientInfo clientInfo) throws Exception {
    return clientInfo.getClientDevice().getPlatform()
        .equals(UserAgentUtil.DevicePlatform.MOBILE.name());
  }
}
