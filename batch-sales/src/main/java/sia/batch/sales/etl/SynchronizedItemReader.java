package sia.batch.sales.etl;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import sia.batch.sales.model.SalesRecord;

public class SynchronizedItemReader implements ItemReader<SalesRecord>, ItemStream {
	
	private ItemReader<SalesRecord> delegate;

	@Override
	public synchronized SalesRecord read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return delegate.read();
	}

	public void setDelegate(ItemReader<SalesRecord> delegate) {
		this.delegate = delegate;
	}
	
	public void close() {
		if(this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).close();
		}
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		if(this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).open(executionContext);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if(this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).update(executionContext);
		}
	}
}
