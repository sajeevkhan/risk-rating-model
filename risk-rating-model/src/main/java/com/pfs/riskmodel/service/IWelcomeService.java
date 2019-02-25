package com.pfs.riskmodel.service;

import com.pfs.riskmodel.resource.EmailId;
import com.pfs.riskmodel.resource.User;

public interface IWelcomeService {

    User getUser();

    User getUserByEmail(EmailId emailId);
}
