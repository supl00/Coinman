package com.gazua.ddeokrok.coinman.chart.model.market;

import com.gazua.ddeokrok.coinman.chart.model.CheckerInfo;
import com.gazua.ddeokrok.coinman.chart.model.Market;
import com.gazua.ddeokrok.coinman.chart.model.Ticker;
import com.gazua.ddeokrok.coinman.chart.model.currency.Currency;
import com.gazua.ddeokrok.coinman.chart.model.currency.VirtualCurrency;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Bit2c extends Market {

	private final static String NAME = "Bit2c";
	private final static String TTS_NAME = "Bit 2c";
	private final static String URL = "https://www.bit2c.co.il/Exchanges/%1$s%2$s/Ticker.json";
	private final static HashMap<String, CharSequence[]> CURRENCY_PAIRS = new LinkedHashMap<>();
	static {
		CURRENCY_PAIRS.put(VirtualCurrency.BTC, new String[]{
				Currency.NIS
		});
		CURRENCY_PAIRS.put(VirtualCurrency.LTC, new String[]{
				Currency.NIS
		});
		CURRENCY_PAIRS.put(VirtualCurrency.BCH, new String[]{
				Currency.NIS
		});
		CURRENCY_PAIRS.put(VirtualCurrency.BTG, new String[]{
				Currency.NIS
		});
	}
	
	public Bit2c() {
		super(NAME, TTS_NAME, CURRENCY_PAIRS);
	}

	@Override
	public String getUrl(int requestId, CheckerInfo checkerInfo) {
		return String.format(URL, checkerInfo.getCurrencyBase(), checkerInfo.getCurrencyCounter());
	}
	
	@Override
	protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
		ticker.bid = jsonObject.getDouble("h");
		ticker.ask = jsonObject.getDouble("l");
		ticker.vol = jsonObject.getDouble("a");
		ticker.last = jsonObject.getDouble("ll");
	}
}
