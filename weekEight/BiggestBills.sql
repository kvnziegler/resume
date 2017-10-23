USE hotel;

select BillID, total
from bill
ORDER BY total DESC
LIMIT 3