package com.gazua.ddeokrok.coinman.chart.model.market;

import com.gazua.ddeokrok.coinman.chart.model.CheckerInfo;
import com.gazua.ddeokrok.coinman.chart.model.Market;
import com.gazua.ddeokrok.coinman.chart.model.Ticker;
import com.gazua.ddeokrok.coinman.chart.model.currency.Currency;
import com.gazua.ddeokrok.coinman.chart.model.currency.VirtualCurrency;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BitKonan extends Market {

	private final static String NAME = "BitKonan";
	private final static String TTS_NAME = "Bit Konan";
	private final static String URL_BTC = "https://bitkonan.com/api/ticker";
	private final static String URL_LTC = "https://bitkonan.com/api/ltc_ticker";
	private final static HashMap<String, CharSequence[]> CURRENCY_PAIRS = new LinkedHashMap<String, CharSequence[]>();
	static {
		CURRENCY_PAIRS.put(VirtualCurrency.BTC, new String[]{
				Currency.USD
			});
		CURRENCY_PAIRS.put(VirtualCurrency.LTC, new String[]{
				Currency.USD
			});
	}
	
	public BitKonan() {
		super(NAME, TTS_NAME, CURRENCY_PAIRS);
	}
	
	@Override
	public String getUrl(int requestId, CheckerInfo checkerInfo) {
		if(VirtualCurrency.BTC.equals(checkerInfo.getCurrencyBase())) {
			return URL_BTC;
		} else {
			return URL_LTC;
		}
	}
	
	@Override
	protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
		ticker.bid = jsonObject.getDouble("bid");
		ticker.ask = jsonObject.getDouble("ask");
		ticker.vol = jsonObject.getDouble("volume");
		ticker.high = jsonObject.getDouble("high");
		ticker.low = jsonObject.getDouble("low");
		ticker.last = jsonObject.getDouble("last");
	}
}
