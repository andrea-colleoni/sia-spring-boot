package corso.batch.etl;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import corso.batch.model.Prodotto;

public class ProductJdbcItemWriter implements ItemWriter<Prodotto> {
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void write(List<? extends Prodotto> items) throws Exception {
		for(Prodotto p : items) {
			jdbcTemplate.update("INSERT INTO `sia-batch`.prodotti " + 
					"(id, descrizione, nome, prezzo) " + 
					"VALUES(?, ?, ?, ?)", p.getId(), p.getDecsrizione(), p.getNome(), p.getPrezzo());
		}
	}

}
