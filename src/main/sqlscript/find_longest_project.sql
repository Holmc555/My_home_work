SELECT 
    p.id AS project_id,
    EXTRACT(YEAR FROM age(p.finish_date, p.start_date)) * 12 + EXTRACT(MONTH FROM age(p.finish_date, p.start_date)) AS month_count
FROM
    project p
ORDER BY
    month_count DESC
LIMIT 1;
