package sia.jpa.rep1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Repo1Test {
	
	@Autowired
	Repo1 r1;

	@Test
	public void test() {
		r1.findAll();
	}

}
