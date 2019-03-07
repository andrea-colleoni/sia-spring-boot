package corso.batch.etl;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;

import corso.batch.model.Prodotto;

public class ReadWriteSkipListener { // implements SkipListener<Prodotto, Prodotto> {

	@OnSkipInRead
	public void onSkipInRead(Throwable t) {
		System.out.println("read error ");
		if (t instanceof FlatFileParseException) {
			FlatFileParseException fex = (FlatFileParseException)t;
			System.out.println(fex.getInput());
		}
		t.printStackTrace();
	}

	public void onSkipInWrite(Prodotto item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	public void onSkipInProcess(Prodotto item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
