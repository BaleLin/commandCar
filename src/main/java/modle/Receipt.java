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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(receiptNumber, receipt.receiptNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(receiptNumber);
    }

}
