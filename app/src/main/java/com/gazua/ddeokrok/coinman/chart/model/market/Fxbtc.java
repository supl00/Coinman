package com.gazua.ddeokrok.coinman.chart.model.market;

import com.gazua.ddeokrok.coinman.chart.model.CheckerInfo;
import com.gazua.ddeokrok.coinman.chart.model.Market;
import com.gazua.ddeokrok.coinman.chart.model.Ticker;
import com.gazua.ddeokrok.coinman.chart.model.currency.Currency;
import com.gazua.ddeokrok.coinman.chart.model.currency.VirtualCurrency;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Fxbtc extends Market {

	private final static String NAME = "FxBtc";
	private final static String TTS_NAME = NAME;
	private final static String URL = "https://www.fxbtc.com/jport?op=query&type=ticker&symbol=%1$s_%2$s";
	private final static HashMap<String, CharSequence[]> CURRENCY_PAIRS = new LinkedHashMap<String, CharSequence[]>();
	static {
		CURRENCY_PAIRS.put(VirtualCurrency.BTC, new String[]{
				Currency.CNY
			});
		CURRENCY_PAIRS.put(VirtualCurrency.LTC, new String[]{
				Currency.CNY,
				VirtualCurrency.BTC
			});
	}
	
	public Fxbtc() {
		super(NAME, TTS_NAME, CURRENCY_PAIRS);
	}

	@Override
	public String getUrl(int requestId, CheckerInfo checkerInfo) {
		return String.format(URL, checkerInfo.getCurrencyBaseLowerCase(), checkerInfo.getCurrencyCounterLowerCase());
	}
	
	@Override
	protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
		final JSONObject tickerObject = jsonObject.getJSONObject("ticker");
		
		ticker.bid = tickerObject.getDouble("bid");
		ticker.ask = tickerObject.getDouble("ask");
		ticker.vol = tickerObject.getDouble("vol");
		ticker.high = tickerObject.getDouble("high");
		ticker.low = tickerObject.getDouble("low");
		ticker.last = tickerObject.getDouble("last_rate");
	}
}
