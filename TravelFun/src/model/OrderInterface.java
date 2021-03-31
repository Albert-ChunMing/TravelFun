package model;

import java.sql.Connection;
import java.util.List;

public interface OrderInterface {
		public void add(List orderlist,Connection conn);
		public List queryAll();
}
