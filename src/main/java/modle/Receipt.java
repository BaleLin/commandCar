package modle;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.Objects;
import java.util.UUID;

public class Receipt {
    String receiptNumber;

    public Receipt(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) {return true;}
        else if (this.receiptNumber.equals(((Receipt)obj).receiptNumber)){
            return true;
        }else {
           return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(receiptNumber);
    }
}
