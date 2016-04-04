package org.ar.linclick.distributed.function.filters;

import org.apache.spark.api.java.function.Function;
import org.ar.linclick.distributed.entity.ClientInfo;
import org.ar.linclick.distributed.util.UserAgentUtil;

/**
 * Filter out all except PC devices.
 */
public class FilterPcDevices implements Function<ClientInfo, Boolean> {
  @Override
  public Boolean call(ClientInfo clientInfo) throws Exception {
    return clientInfo.getClientDevice().getPlatform().equals(UserAgentUtil.DevicePlatform.PC.name());
  }
}
