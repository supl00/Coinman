package com.gazua.ddeokrok.coinman.chart.model.market;

import com.gazua.ddeokrok.coinman.chart.model.CheckerInfo;
import com.gazua.ddeokrok.coinman.chart.model.Market;
import com.gazua.ddeokrok.coinman.chart.model.Ticker;
import com.gazua.ddeokrok.coinman.chart.model.currency.Currency;
import com.gazua.ddeokrok.coinman.chart.model.currency.VirtualCurrency;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SurBitcoin extends Market {

	private final static String NAME = "SurBitcoin";
	private final static String TTS_NAME = "Sur Bitcoin";
	private final static String URL = "https://api.blinktrade.com/api/v1/%2$s/ticker?crypto_currency=%1$s";
	private final static HashMap<String, CharSequence[]> CURRENCY_PAIRS = new LinkedHashMap<String, CharSequence[]>();
	static {
		CURRENCY_PAIRS.put(VirtualCurrency.BTC, new String[]{
				Currency.VEF
		});
	}

	public SurBitcoin() {
		super(NAME, TTS_NAME, CURRENCY_PAIRS);
	}

	@Override
	public String getUrl(int requestId, CheckerInfo checkerInfo) {
		return String.format(URL, checkerInfo.getCurrencyBase(), checkerInfo.getCurrencyCounter());
	}

	@Override
	protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
		ticker.bid = jsonObject.getDouble("buy");
		ticker.ask = jsonObject.getDouble("sell");
		ticker.vol = jsonObject.getDouble("vol");
		ticker.high = jsonObject.getDouble("high");
		ticker.low = jsonObject.getDouble("low");
		ticker.last = jsonObject.getDouble("last");
	}
}
