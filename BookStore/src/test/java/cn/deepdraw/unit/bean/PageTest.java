package cn.deepdraw.unit.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cn.deepdraw.bean.Page;

public class PageTest {

	@Test
	public void getStart_happyPath() {

		Page page = new Page(1);

		assertEquals(0, page.getStart());
	}

	@Test
	public void getPageSize_happyPath() {

		Page page = new Page(1, 2, 3);

		assertEquals(2, page.getPageSize());
	}

	@Test
	public void getMaxPage_happyPath() {

		Page page = new Page(1, 2, 3);

		assertEquals(2, page.getMaxPage());
	}

	@Test
	public void setTotalNum_happyPath() {

		Page page = new Page(1, 2, 3);

		assertEquals(2, page.getMaxPage());
		assertEquals(1, page.getLastPageSize());
	}

	@Test
	public void getLastPageCount_happyPath() {

		Page page = new Page(1, 2, 3);

		assertEquals(1, page.getLastPageSize());
	}
}
