SELECT NAME, SALARY FROM WORKER WHERE SALARY = (SELECT MAX(SALARY) FROM WORKER);
