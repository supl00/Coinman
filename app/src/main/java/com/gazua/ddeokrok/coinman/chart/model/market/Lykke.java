package com.gazua.ddeokrok.coinman.chart.model.market;

import com.gazua.ddeokrok.coinman.chart.model.CheckerInfo;
import com.gazua.ddeokrok.coinman.chart.model.CurrencyPairInfo;
import com.gazua.ddeokrok.coinman.chart.model.Market;
import com.gazua.ddeokrok.coinman.chart.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Lykke extends Market {

	private final static String NAME = "Lykke";
	private final static String TTS_NAME = NAME;
	private final static String URL = "https://public-api.lykke.com/api/Market/%1$s";
	private final static String URL_CURRENCY_PAIRS = "https://public-api.lykke.com/api/AssetPairs/dictionary";
	
	public Lykke() {
		super(NAME, TTS_NAME, null);
	}

	@Override
	public String getUrl(int requestId, CheckerInfo checkerInfo) {
		return String.format(URL, checkerInfo.getCurrencyPairId());
	}
	
	@Override
	protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
		ticker.bid = jsonObject.getDouble("bid");
		ticker.ask = jsonObject.getDouble("ask");
		ticker.vol = jsonObject.getDouble("volume24H");
		ticker.last = jsonObject.getDouble("lastPrice");
	}

	// ====================
	// Get currency pairs
	// ====================
	@Override
	public String getCurrencyPairsUrl(int requestId) {
		return URL_CURRENCY_PAIRS;
	}
	
	@Override
	protected void parseCurrencyPairs(int requestId, String responseString, List<CurrencyPairInfo> pairs) throws Exception {
		final JSONArray jsonArray = new JSONArray(responseString);
		for (int i = 0; i < jsonArray.length(); ++i) {
			final JSONObject pairJsonObject = jsonArray.getJSONObject(i);
			pairs.add(new CurrencyPairInfo(
					pairJsonObject.getString("baseAssetId"),
					pairJsonObject.getString("quotingAssetId"),
					pairJsonObject.getString("id")));
		}
	}
}