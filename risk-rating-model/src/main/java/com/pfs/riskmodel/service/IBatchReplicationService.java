package com.pfs.riskmodel.service;

import com.pfs.riskmodel.resource.EmailId;
import com.pfs.riskmodel.resource.User;

import javax.servlet.http.HttpServletRequest;

public interface IBatchReplicationService {

  public void replicationService(HttpServletRequest request, Integer loanNumberFrom, Integer loanNumberTo);
}
