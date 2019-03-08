package sia.batch.sales.etl;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import sia.batch.sales.model.SalesRecord;

public class SalesRecordFieldSetMapper implements FieldSetMapper<SalesRecord> {

	@Override
	public SalesRecord mapFieldSet(FieldSet fieldSet) throws BindException {
		SalesRecord sr = new SalesRecord();
		sr.setOrderID(fieldSet.readLong("orderID"));
		sr.setOrderDate(fieldSet.readDate("orderDate"));
		sr.setShipDate(fieldSet.readDate("shipDate"));
		sr.setRegion(fieldSet.readString("region"));
		sr.setCountry(fieldSet.readString("country"));
		sr.setItemType(fieldSet.readString("itemType"));
		sr.setSalesChannel(fieldSet.readString("salesChannel"));
		sr.setOrderPriority(fieldSet.readString("orderPriority"));
		sr.setUnitsSold(fieldSet.readInt("unitsSold"));
		sr.setUnitPrice(fieldSet.readBigDecimal("unitPrice"));
		sr.setUnitCost(fieldSet.readBigDecimal("unitCost"));
		sr.setTotalRevenue(fieldSet.readBigDecimal("totalRevenue"));
		sr.setTotalCost(fieldSet.readBigDecimal("totalCost"));
		sr.setTotalProfit(fieldSet.readBigDecimal("totalProfit"));

		return sr;
	}

}
