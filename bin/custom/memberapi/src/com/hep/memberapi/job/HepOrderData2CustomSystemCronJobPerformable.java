package com.hep.memberapi.job;

import com.hep.memberapi.manager.OrderManager;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author William Zeky
 * @Title;
 * @Description
 * @date 2018/1/25 16:49
 */
public class HepOrderData2CustomSystemCronJobPerformable extends AbstractJobPerformable<CronJobModel> {

    @Autowired
    private OrderManager orderManager;

    private static final Logger LOG = Logger.getLogger
            (HepOrderData2CustomSystemCronJobPerformable.class);

    @Override
    public PerformResult perform(final CronJobModel cronJobModel) {

        LOG.info("\n\n==========" + "Start " +
                "HepOrderData2CustomSystemCronJobPerformable" +"==========");
        LOG.info("\n==========" + new Date() + "==========");

        orderManager.pushOrders();

        LOG.info("\n==========" + "OrderCronJob Called Finished" +
                "==========\n\n");
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}