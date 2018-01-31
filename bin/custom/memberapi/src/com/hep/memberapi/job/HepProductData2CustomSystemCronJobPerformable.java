package com.hep.memberapi.job;

import com.hep.memberapi.manager.ProductManager;
import de.hybris.platform.acceleratorservices.dataexport.googlelocal.model
        .Product;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.soap.Addressing;
import java.util.Date;

/**
 * @author William Zeky
 * @Title;
 * @Description
 * @date 2018/1/25 17:01
 */
public class HepProductData2CustomSystemCronJobPerformable extends
        AbstractJobPerformable<CronJobModel> {

    @Autowired
    private ProductManager productManager;

    private static final Logger LOG = Logger.getLogger
            (HepOrderData2CustomSystemCronJobPerformable.class);

    @Override
    public PerformResult perform(final CronJobModel cronJobModel) {
        LOG.info("\n\n==========" + "Start " +
                "HepProductData2CustomSystemCronJobPerformable" +"==========");
        LOG.info("\n==========" + new Date() + "==========");

        productManager.pushProducts();

        LOG.info("\n==========" + "ProductCronJob Called Finished" +
                "==========\n\n");
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
