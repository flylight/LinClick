package org.ar.linclick.functions.filters;

import org.apache.spark.api.java.function.Function;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.utils.UserAgentUtil;

/**
 * Filter out all except PC devices.
 */
public class FilterPcDevices implements Function<ClientInfo, Boolean> {
  @Override
  public Boolean call(ClientInfo clientInfo) throws Exception {
    return clientInfo.getClientDevice().getPlatform().equals(UserAgentUtil.DevicePlatform.PC.name());
  }
}
