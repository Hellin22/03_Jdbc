package com.ohgiraffers.section03.delete;

import com.ohgiraffers.section03.delete.MenuRepository;

import java.sql.Connection;
import static com.ohgiraffers.common.JDBCTemplate.*;


public class MenuService {
    public void deleteMenu(Menu deleteMenu) {
        Connection con = getConnection();
        int result = 0;

        MenuRepository repository = new MenuRepository();
        result = repository.deleteMenu(con, deleteMenu);

        if(result > 0) commit(con);
        else rollback(con);

        close(con);
    }
}
