import yfinance as yf
import sys
import json

def fetch_index_data(index_symbol):
    try:
        index = yf.Ticker(index_symbol)
        history = index.history(period="1mo")["Close"]

        # Check if the history data is empty
        if history.empty:
            raise ValueError(f"No historical data found for symbol {index_symbol}")

        # Convert the Timestamp index to string to be used as keys in the dictionary
        history_dict = {str(date): price for date, price in history.items()}

        data = {
            "symbol": index_symbol,
            "name": index.info.get("longName", "N/A"),
            "current_price": history.iloc[-1],  # Latest price
            "history": history_dict  # History as a dictionary with string dates
        }
        print(json.dumps(data))
    except Exception as e:
        print(json.dumps({"error": str(e)}))

if __name__ == "__main__":
    symbol = sys.argv[1]  # Get symbol from Java call
    fetch_index_data(symbol)
