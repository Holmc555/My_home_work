SELECT 
    p.ID AS PROJECT_ID,
    SUM(w.SALARY) * TIMESTAMPDIFF(MONTH, p.START_DATE, p.FINISH_DATE) AS PRICE
FROM 
    project_worker pw
JOIN 
    project p ON pw.PROJECT_ID = p.ID
JOIN 
    worker w ON pw.WORKER_ID = w.ID
GROUP BY 
    p.ID
ORDER BY 
    PRICE DESC;