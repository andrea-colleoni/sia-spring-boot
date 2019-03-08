package sia.batch.sales.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import sia.batch.sales.model.SalesRecord;

public class SalesRecordProcessor implements ItemProcessor<SalesRecord, SalesRecord> {
	
	private static Logger log = LoggerFactory.getLogger(SalesRecordProcessor.class);

	@Override
	public SalesRecord process(SalesRecord item) throws Exception {
		long millis = (item.getShipDate().getTime() - item.getOrderDate().getTime());
		item.setElapsedDays((int)(millis / (1000 * 60 *60 * 24)));
		// log.info("elapsed days => " + item.getElapsedDays());
		return item;
	}

}
