package sia.jpa.rep2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sia.jpa.rep2.Repo2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Repo2Test {
	
	@Autowired
	Repo2 r2;

	@Test
	public void test() {
		r2.findAll();
	}

}
