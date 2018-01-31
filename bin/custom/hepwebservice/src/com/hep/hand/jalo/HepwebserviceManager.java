/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.hep.hand.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;
import com.hep.hand.constants.HepwebserviceConstants;

@SuppressWarnings("PMD")
public class HepwebserviceManager extends GeneratedHepwebserviceManager
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger( HepwebserviceManager.class.getName() );
	
	public static final HepwebserviceManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (HepwebserviceManager) em.getExtension(HepwebserviceConstants.EXTENSIONNAME);
	}
	
}
