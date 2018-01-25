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
package com.hep.memberapi.setup;

import static com.hep.memberapi.constants.MemberapiConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.hep.memberapi.constants.MemberapiConstants;
import com.hep.memberapi.service.MemberapiService;


@SystemSetup(extension = MemberapiConstants.EXTENSIONNAME)
public class MemberapiSystemSetup
{
	private final MemberapiService memberapiService;

	public MemberapiSystemSetup(final MemberapiService memberapiService)
	{
		this.memberapiService = memberapiService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		memberapiService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return MemberapiSystemSetup.class.getResourceAsStream("/memberapi/sap-hybris-platform.png");
	}
}
