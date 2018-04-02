/**
 * Program Name: Tester.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, March 11, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the tester class for the entire project.
 * <p>
 */

public class Tester<E> {

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		HashTable<RomanNumeral> ht1, ht2, ht3;
		ht1 = new HashSC<RomanNumeral>(new RomanValueHasher(), new RomanValueComparator());
		ht2 = new HashQP<RomanNumeral>(new RomanStringHasher1(), new RomanStringComparator());
		ht3 = new HashQP<RomanNumeral>(new RomanStringHasher2(), new RomanStringComparator());
		RomanNumeral r = fillHashTables(ht1, ht2, ht3);
		displayTheTables(ht1, ht2, ht3);
		displayStats(ht1, ht2, ht3);
		System.out.println("\nTesting HashSC:");
		testHashTableOperations(ht1, r);
		System.out.println("\nTesting HashQP #1:");
		testHashTableOperations(ht2, r);
		testHashTables(ht1, ht2, r);
	}

	/**
	 * Tests HashTable operations such as getEntry, remove, and insert
	 * 
	 * @param ht
	 *            RomanNumeral object being tested
	 * @param romNum
	 *            last RomanNumeral object inserted into HashTable
	 */
	public static void testHashTableOperations(HashTable<RomanNumeral> ht, RomanNumeral romNum) {
		RomanNumeral rn = new RomanNumeral(4001);
		System.out.println("\nThe return value of calling getEntry for " + rn + " is " + ht.getEntry(rn));
		System.out.println("The return value of calling remove for " + rn + " is " + ht.remove(rn));
		System.out.println("The return value of calling insert for " + romNum + " is " + ht.insert(romNum));
	}

	/**
	 * Displays additional statistics for the HashTable objects
	 * 
	 * @param ht1
	 *            HashTable object 1
	 * @param ht2
	 *            HashTable object 2
	 * @param ht3
	 *            HashTable object 3
	 */
	public static void displayStats(HashTable<RomanNumeral> ht1, HashTable<RomanNumeral> ht2,
			HashTable<RomanNumeral> ht3) {
		System.out.println("\nHashSC statistics:");
		ht1.displayStatistics();
		System.out.println("\nHashQP #1 statistics:");
		ht2.displayStatistics();
		System.out.println("\nHashQP #2 statistics:");
		ht3.displayStatistics();
	}

	/**
	 * Displays the HashTables by calling the displayTable method in each HashTable
	 * object
	 * 
	 * @param ht1
	 *            HashTable object 1
	 * @param ht2
	 *            HashTable object 2
	 * @param ht3
	 *            HashTable object 3
	 */
	public static void displayTheTables(HashTable<RomanNumeral> ht1, HashTable<RomanNumeral> ht2,
			HashTable<RomanNumeral> ht3) {
		System.out.println("HashSC with int value key has: \n");
		ht1.displayTable();
		System.out.println("\nHashQP #1 with String key has: \n");
		ht2.displayTable();
		System.out.println("\nHashQP #2 with String key has: \n");
		ht3.displayTable();
	}

	/**
	 * Fills the HashTable objects with pseudorandom data
	 * 
	 * @param ht1
	 *            HashTable object 1
	 * @param ht2
	 *            HashTable object 2
	 * @param ht3
	 *            HashTable object 3
	 * @return last RomanNumeral object inserted into HashTables
	 */
	public static RomanNumeral fillHashTables(HashTable<RomanNumeral> ht1, HashTable<RomanNumeral> ht2,
			HashTable<RomanNumeral> ht3) {
		int numEntries = (int) (Math.random() * 301 + 100);
		System.out.println("Number of entries being inserted into the table = " + numEntries);
		boolean[] boolArray = new boolean[4001];
		int counter = 0;
		RomanNumeral romNum = null;
		while (counter < numEntries) {
			int rand = (int) (Math.random() * 4000 + 1);
			if (!boolArray[rand]) {
				romNum = new RomanNumeral(rand);
				ht1.insert(romNum);
				ht2.insert(romNum);
				ht3.insert(romNum);
				boolArray[rand] = true;
				counter++;
			}
		}
		return romNum;
	}

	/**
	 * Additional tester for the HashTable objects
	 * 
	 * @param tableSC
	 *            HashSC object
	 * @param tableQP
	 *            HashQP object
	 * @param lastRoman
	 *            last RomanNumeral object to be inserted into HashTables
	 */
	public static void testHashTables(HashTable<RomanNumeral> tableSC, HashTable<RomanNumeral> tableQP,
			RomanNumeral lastRoman) {
		RomanNumeral targetRoman = new RomanNumeral(lastRoman.getValue()); // make copy
		RomanNumeral tempRoman;

		String tableName = "HashSC";
		HashTable<RomanNumeral> currentTable = tableSC;

		for (int count = 0; count < 2; ++count) {
			System.out.println("\nCalling getEntry in " + tableName + " for " + targetRoman);
			tempRoman = currentTable.getEntry(targetRoman);

			if (tempRoman != null) {
				System.out.println(
						"Retrieved in " + tableName + ", RomanNumeral: " + tempRoman + ", now trying to delete it");
				// now delete it
				System.out.println("Calling remove for " + targetRoman + " from " + tableName + " returns "
						+ currentTable.remove(targetRoman));
				System.out.println("Calling remove for " + targetRoman + " from " + tableName + " AGAIN returns "
						+ currentTable.remove(targetRoman));
			} else
				System.out.println("Error in " + tableName + ": can't retrieve " + targetRoman);

			tableName = "HashQP";
			currentTable = tableQP;
		} // end for
		System.out.println("\nNow the statistics for HashSC are");
		tableSC.displayStatistics();
		System.out.println("\nNow the statistics for one of the HashQPs are");
		tableQP.displayStatistics();
	} // end testHashTables

/* EXECUTION RESULTS:
Number of entries being inserted into the table = 243
HashSC with int value key has: 

0: MMMCMXL = 3940
1: MCCCLXXX = 1380
2: MMDCCLX = 2760
3: MCCCLXXXII = 1382 -> MMMCMXLIII = 3943
4: DCCXCII = 792
5: MMMCCCLIV = 3354 -> MMDCCLXIII = 2763 -> MMCMLX = 2960
6: ----
7: MDLXXXIII = 1583
8: MCXC = 1190
9: ----
10: MCCCLXXXIX = 1389
11: ----
12: DCCC = 800 -> CMXCVII = 997 -> MCCCXCI = 1391
13: MCMLXXXIII = 1983
14: MMDCCLXXII = 2772 -> CCXI = 211
15: MMCLXXXII = 2182
16: CDX = 410
17: MMMCLXIX = 3169 -> MMMCCCLXVI = 3366 -> DCCCV = 805 -> MMCLXXXIV = 2184
18: MCCCXCVII = 1397 -> MMMCLXX = 3170
19: ----
20: ----
21: MMMCCCLXX = 3370 -> MDXCVII = 1597
22: CDXVI = 416 -> XXII = 22
23: CCXX = 220
24: MCMXCIV = 1994
25: MX = 1010
26: XXVI = 26 -> CDXX = 420 -> DCCCXIV = 814 -> MMMCCCLXXV = 3375
27: XXVII = 27
28: ----
29: CDXXIII = 423 -> MMDXC = 2590 -> DCCCXVII = 817
30: MDCCCIII = 1803
31: CDXXV = 425
32: MMCXCIX = 2199
33: MMMDCCLXXVI = 3776 -> CDXXVII = 427
34: MMDXCV = 2595 -> DCXXV = 625
35: ----
36: MMCMXCI = 2991
37: DCXXVIII = 628
38: MMDCCXCVI = 2796
39: MMCDIII = 2403
40: MMCMXCV = 2995
41: ----
42: ----
43: CCXL = 240
44: XLIV = 44 -> MXXIX = 1029 -> MMMCCCXCIII = 3393
45: MMXV = 2015 -> CCXLII = 242
46: MMMCXCVIII = 3198
47: ----
48: CDXLII = 442
49: MXXXIV = 1034
50: ----
51: ----
52: ----
53: MMMVIII = 3008 -> CCL = 250
54: MMMIX = 3009 -> MCCXXXVI = 1236
55: MMDCCCXIII = 2813 -> MMXXV = 2025
56: ----
57: MMMDCIII = 3603 -> MDCCCXXX = 1830
58: MMMCCX = 3210
59: MMMCMXCIX = 3999
60: CCLVII = 257
61: MDCCCXXXIV = 1834 -> MCCXLIII = 1243
62: MMCDXXVI = 2426
63: MMMCDXII = 3412 -> MDCCCXXXVI = 1836 -> MMCCXXX = 2230 -> MMCDXXVII = 2427
64: MMCCXXXI = 2231 -> MMMXIX = 3019
65: MMDCXXVI = 2626
66: ----
67: MMDCXXVIII = 2628 -> MMMCCXIX = 3219 -> MDCCCXL = 1840
68: MMMCCXX = 3220 -> MMCDXXXII = 2432
69: ----
70: ----
71: MMCCXXXVIII = 2238 -> MMXLI = 2041 -> CCLXVIII = 268
72: MMCCXXXIX = 2239 -> MCDLI = 1451
73: MCDLII = 1452
74: ----
75: MMMCDXXIV = 3424 -> DCLXVI = 666
76: CCLXXIII = 273
77: MMCDXLI = 2441 -> MMDCCCXXXV = 2835 -> DCLXVIII = 668
78: MMDCXXXIX = 2639 -> MDCCCLI = 1851
79: MMCCXLVI = 2246 -> LXXIX = 79
80: CCLXXVII = 277
81: MMCDXLV = 2445 -> MLXVI = 1066
82: MDCLVIII = 1658
83: MLXVIII = 1068 -> MMLIII = 2053
84: MCCLXVI = 1266 -> MDCLX = 1660
85: ----
86: CCLXXXIII = 283
87: MMMCCXXXIX = 3239
88: CCLXXXV = 285 -> DCLXXIX = 679
89: MMMDCCCXXXII = 3832
90: MMCCLVII = 2257
91: ----
92: ----
93: MDCLXIX = 1669 -> MMCCLX = 2260
94: ----
95: ----
96: MDCLXXII = 1672 -> MMDCLVII = 2657
97: MLXXXII = 1082 -> MMCDLXI = 2461
98: MMDCCCLVI = 2856
99: MMCCLXVI = 2266 -> MCDLXXVIII = 1478
100: MMMDCCCXLIII = 3843 -> MDCLXXVI = 1676
101: MMCDLXV = 2465
102: ----
103: MCCLXXXV = 1285
104: ----
105: ----
106: MMMDCCCXLIX = 3849 -> MMCCLXXIII = 2273
107: MMLXXVII = 2077
108: MXCIII = 1093 -> MMMLXIII = 3063
109: CIX = 109
110: DIV = 504 -> MDCCCLXXXIII = 1883 -> MMLXXX = 2080
111: CCCVIII = 308
112: MMMDCCCLV = 3855 -> MMMCCLXIV = 3264
113: MXCVIII = 1098 -> MMLXXXIII = 2083 -> MMMCDLXII = 3462
114: MDCXC = 1690 -> MDCCCLXXXVII = 1887
115: MDCXCI = 1691 -> MMCCLXXXII = 2282
116: MCI = 1101 -> MDCXCII = 1692 -> MMMLXXI = 3071 -> MMCCLXXXIII = 2283
117: DCCVIII = 708
118: CMVI = 906
119: ----
120: ----
121: MMMCDLXX = 3470
122: MDCCCXCV = 1895
123: MCCCV = 1305 -> MMDCCCLXXXI = 2881
124: MCCCVI = 1306 -> MMMDCCCLXVII = 3867 -> CMXII = 912 -> MCIX = 1109
125: ----
126: CCCXXIII = 323 -> MDV = 1505
127: DXXI = 521 -> MMMCCLXXIX = 3279 -> MMMDCCCLXX = 3870
128: MMMCDLXXVII = 3477 -> MMMLXXXIII = 3083 -> MCCCX = 1310 -> MMMCCLXXX = 3280
129: MMCDXCIII = 2493
130: ----
131: DCCXXII = 722
132: MCMV = 1905 -> MMCDXCVI = 2496 -> MMMCDLXXXI = 3481
133: ----
134: MMDCCCXCII = 2892 -> MMMDCCCLXXVII = 3877
135: ----
136: MDXV = 1515 -> MMCVI = 2106
137: MMDI = 2501
138: MMMDCCCLXXXI = 3881 -> MMCVIII = 2108
139: CXXXIX = 139
140: ----
141: ----
142: MCXXVII = 1127 -> MMCXII = 2112
143: MMDVII = 2507
144: MMMCCXCVI = 3296
145: MMDIX = 2509 -> MMMDCXCI = 3691
146: DXL = 540 -> MCXXXI = 1131 -> MCMXIX = 1919
147: MMDCCVIII = 2708 -> CCCXLIV = 344 -> CMXXXV = 935
148: DXLII = 542
149: ----
150: MMMDCCCXCIII = 3893 -> CL = 150
151: MCXXXVI = 1136
152: MMMDCXCVIII = 3698
153: MMDCCXIV = 2714
154: MMMDCCCXCVII = 3897
155: ----
156: MMDCCXVII = 2717 -> MMCMXIV = 2914 -> MMCCCXXIII = 2323
157: CMXLV = 945
158: ----
159: ----
160: CMXLVIII = 948 -> MDCCXXXVI = 1736
161: MMCCCXXVIII = 2328
162: ----
163: ----
164: MCCCXLVI = 1346
165: MCCCXLVII = 1347
166: MMMCMIX = 3909
167: ----
168: ----
169: MCLIV = 1154 -> MCMXLII = 1942
170: ----
171: MDL = 1550
172: ----
173: ----
174: CMLXII = 962
175: MMCXLV = 2145
176: MMMCCCXXVIII = 3328
177: ----
178: DCCLXIX = 769
179: CMLXVII = 967
180: MCMLIII = 1953
181: CCCLXXVIII = 378
182: MCMLV = 1955
183: ----
184: DCCLXXV = 775 -> MCCCLXVI = 1366
185: ----
186: CLXXXVI = 186 -> MCLXXI = 1171
187: MMMDCCXXXIII = 3733 -> MMMCCCXXXIX = 3339
188: MCMLXI = 1961 -> MMMDCCXXXIV = 3734
189: DLXXXIII = 583
190: CXC = 190
191: CCCLXXXVIII = 388 -> DLXXXV = 585
192: ----
193: MMCMLI = 2951 -> MCCCLXXV = 1375 -> CXCIII = 193
194: CCCXCI = 391
195: ----
196: CMLXXXIV = 984

HashQP #1 with String key has: 

0: ----
1: DLXXXV = 585
2: MMDIX = 2509
3: ----
4: ----
5: MMMCLXX = 3170
6: ----
7: ----
8: ----
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: MMMCMIX = 3909
23: ----
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: MMMCLXIX = 3169
31: CCLXXVII = 277
32: ----
33: ----
34: ----
35: XXVI = 26
36: MLXXXII = 1082
37: MMMDCCCXLIII = 3843
38: MMMCCXIX = 3219
39: MMMDCCCLV = 3855
40: MMXXV = 2025
41: ----
42: MCMLV = 1955
43: ----
44: MMMDCCCXLIX = 3849
45: DXXI = 521
46: MMCCXXXVIII = 2238
47: ----
48: MMMDCCCXCIII = 3893
49: ----
50: ----
51: ----
52: ----
53: ----
54: MMDCCXIV = 2714
55: ----
56: ----
57: MCDLXXVIII = 1478
58: ----
59: ----
60: ----
61: ----
62: ----
63: ----
64: ----
65: ----
66: ----
67: ----
68: ----
69: MXCIII = 1093
70: ----
71: MCCXLIII = 1243
72: ----
73: ----
74: ----
75: ----
76: ----
77: ----
78: ----
79: ----
80: ----
81: ----
82: ----
83: ----
84: ----
85: ----
86: MCCLXVI = 1266
87: ----
88: ----
89: ----
90: MCCXXXVI = 1236
91: ----
92: ----
93: ----
94: ----
95: ----
96: ----
97: ----
98: ----
99: ----
100: ----
101: ----
102: ----
103: ----
104: ----
105: ----
106: ----
107: ----
108: ----
109: ----
110: MMCXII = 2112
111: ----
112: ----
113: ----
114: CMXII = 912
115: ----
116: ----
117: ----
118: ----
119: MMMCCCLIV = 3354
120: DXLII = 542
121: ----
122: ----
123: ----
124: CDXX = 420
125: ----
126: MMXV = 2015
127: ----
128: ----
129: ----
130: ----
131: ----
132: ----
133: ----
134: ----
135: MLXVIII = 1068
136: MMDCCXCVI = 2796
137: ----
138: ----
139: MMCCXXXI = 2231
140: ----
141: ----
142: ----
143: MMCCCXXVIII = 2328
144: ----
145: ----
146: ----
147: MDCXCI = 1691
148: ----
149: ----
150: ----
151: ----
152: ----
153: ----
154: ----
155: ----
156: MCLIV = 1154
157: ----
158: ----
159: ----
160: ----
161: MDCCCXXXVI = 1836
162: MMDCCXVII = 2717
163: MMDCCCXIII = 2813
164: CL = 150
165: MMCDXXVII = 2427
166: ----
167: DCCLXIX = 769
168: MDXCVII = 1597
169: MMMCCXXXIX = 3239
170: MMDI = 2501
171: ----
172: DCCCXIV = 814
173: ----
174: ----
175: ----
176: ----
177: ----
178: ----
179: MMCCLX = 2260
180: ----
181: ----
182: ----
183: ----
184: MMCDXCIII = 2493
185: ----
186: MMMCCCLXVI = 3366
187: MCCCXCI = 1391
188: ----
189: ----
190: MDCCCXXX = 1830
191: ----
192: ----
193: ----
194: ----
195: ----
196: ----
197: ----
198: ----
199: ----
200: ----
201: ----
202: ----
203: CXC = 190
204: MMMCDLXII = 3462
205: MMDVII = 2507
206: ----
207: ----
208: MDCCCXCV = 1895
209: ----
210: ----
211: ----
212: ----
213: ----
214: MMMDCXCI = 3691
215: ----
216: MMCMXIV = 2914
217: MDCCXXXVI = 1736
218: ----
219: ----
220: ----
221: ----
222: ----
223: ----
224: ----
225: ----
226: ----
227: ----
228: MCXXXI = 1131
229: MCIX = 1109
230: ----
231: ----
232: CCL = 250
233: ----
234: MMCXLV = 2145
235: ----
236: ----
237: ----
238: MCMXCIV = 1994
239: CMXLV = 945
240: CMXXXV = 935
241: MMCXCIX = 2199
242: CCLVII = 257
243: CMXCVII = 997
244: MMDCXXVI = 2626
245: MDCXCII = 1692
246: ----
247: MMMDCCCLXX = 3870
248: DIV = 504
249: MCCCLXXXII = 1382
250: MMMDCCXXXIII = 3733
251: MMDCCLX = 2760
252: ----
253: ----
254: MMMCCCXXXIX = 3339
255: MCCCLXXXIX = 1389
256: CDXLII = 442
257: MCCCXLVII = 1347
258: ----
259: ----
260: CXXXIX = 139
261: ----
262: ----
263: MCCCXCVII = 1397
264: ----
265: ----
266: ----
267: ----
268: ----
269: ----
270: ----
271: ----
272: ----
273: MMMCCCLXXV = 3375
274: ----
275: ----
276: ----
277: MMCDLXI = 2461
278: ----
279: ----
280: ----
281: MMMCCX = 3210
282: DLXXXIII = 583
283: ----
284: ----
285: CDX = 410
286: ----
287: ----
288: MDCCCXL = 1840
289: ----
290: MMCDLXV = 2465
291: MMLIII = 2053
292: ----
293: CMLXXXIV = 984
294: ----
295: ----
296: ----
297: ----
298: ----
299: ----
300: ----
301: ----
302: ----
303: ----
304: ----
305: MMCMLI = 2951
306: ----
307: MMMDCXCVIII = 3698
308: MLXVI = 1066
309: MMCCLVII = 2257
310: ----
311: MMMDCCCXCVII = 3897
312: ----
313: ----
314: ----
315: ----
316: ----
317: ----
318: MMCLXXXII = 2182
319: ----
320: MMCMLX = 2960
321: MMCDXLI = 2441
322: ----
323: ----
324: ----
325: MMMCMXLIII = 3943
326: CCCVIII = 308
327: ----
328: DCCCXVII = 817
329: ----
330: ----
331: MMCLXXXIV = 2184
332: MMCCCXXIII = 2323
333: ----
334: MMCDXLV = 2445
335: CCXI = 211
336: ----
337: CCXL = 240
338: MMCCXXX = 2230
339: ----
340: MMCCLXVI = 2266
341: ----
342: ----
343: MMDXCV = 2595
344: ----
345: MDCLX = 1660
346: ----
347: ----
348: ----
349: CCXX = 220
350: ----
351: XXII = 22
352: MMCCXXXIX = 2239
353: MDCLXXVI = 1676
354: ----
355: ----
356: ----
357: ----
358: ----
359: MDCCCLXXXIII = 1883
360: MMMCCLXXX = 3280
361: ----
362: ----
363: ----
364: MXXIX = 1029
365: MCCCV = 1305
366: ----
367: MCCCX = 1310
368: MMMIX = 3009
369: ----
370: MCI = 1101
371: MMMCXCVIII = 3198
372: MMMDCCCXXXII = 3832
373: ----
374: CCCXCI = 391
375: MCMV = 1905
376: ----
377: ----
378: MMCCXLVI = 2246
379: MCCCVI = 1306
380: MMXLI = 2041
381: ----
382: ----
383: MMCVIII = 2108
384: ----
385: ----
386: ----
387: ----
388: ----
389: ----
390: ----
391: ----
392: DCXXVIII = 628
393: ----
394: ----
395: ----
396: ----
397: MMCDIII = 2403
398: ----
399: ----
400: ----
401: CMVI = 906
402: ----
403: ----
404: ----
405: MCMXIX = 1919
406: ----
407: ----
408: ----
409: ----
410: MDL = 1550
411: MXXXIV = 1034
412: ----
413: ----
414: ----
415: ----
416: ----
417: ----
418: ----
419: ----
420: MDV = 1505
421: ----
422: MMCDXCVI = 2496
423: ----
424: ----
425: MMMCMXCIX = 3999
426: ----
427: ----
428: ----
429: ----
430: DCLXXIX = 679
431: ----
432: ----
433: ----
434: ----
435: ----
436: MMMCCXX = 3220
437: ----
438: MCCCLXVI = 1366
439: ----
440: ----
441: ----
442: ----
443: ----
444: ----
445: ----
446: ----
447: ----
448: DCLXVI = 666
449: ----
450: ----
451: MMDCXXXIX = 2639
452: ----
453: ----
454: ----
455: ----
456: ----
457: MCMLXI = 1961
458: MMMDCCXXXIV = 3734
459: ----
460: MCDLI = 1451
461: ----
462: ----
463: ----
464: MMMCDXII = 3412
465: MMMLXIII = 3063
466: CIX = 109
467: ----
468: MMLXXXIII = 2083
469: ----
470: ----
471: MMMLXXI = 3071
472: MCCCXLVI = 1346
473: ----
474: ----
475: ----
476: MMCCLXXXII = 2282
477: ----
478: CCLXVIII = 268
479: MMCDXXVI = 2426
480: MMCCLXXXIII = 2283
481: ----
482: ----
483: ----
484: MMDCLVII = 2657
485: ----
486: ----
487: ----
488: ----
489: ----
490: MMDCXXVIII = 2628
491: MDCCCXXXIV = 1834
492: ----
493: ----
494: ----
495: ----
496: ----
497: ----
498: ----
499: ----
500: ----
501: ----
502: ----
503: MCLXXI = 1171
504: MCXXVII = 1127
505: ----
506: ----
507: ----
508: ----
509: ----
510: ----
511: ----
512: MMCDXXXII = 2432
513: ----
514: MMDCCLXXII = 2772
515: CDXXIII = 423
516: ----
517: ----
518: ----
519: ----
520: ----
521: ----
522: ----
523: ----
524: ----
525: MCCCLXXV = 1375
526: ----
527: MCCCLXXX = 1380
528: ----
529: ----
530: ----
531: ----
532: ----
533: ----
534: CCLXXXV = 285
535: MCXXXVI = 1136
536: MMDXC = 2590
537: ----
538: MMMVIII = 3008
539: ----
540: ----
541: ----
542: ----
543: ----
544: MMDCCCXXXV = 2835
545: ----
546: MMMCDLXX = 3470
547: CCCXLIV = 344
548: ----
549: ----
550: MX = 1010
551: ----
552: ----
553: ----
554: ----
555: ----
556: ----
557: MDXV = 1515
558: ----
559: ----
560: MMMXIX = 3019
561: ----
562: ----
563: ----
564: ----
565: MMMCMXL = 3940
566: CCLXXIII = 273
567: ----
568: ----
569: CCLXXXIII = 283
570: ----
571: XXVII = 27
572: CMLXVII = 967
573: ----
574: ----
575: ----
576: ----
577: ----
578: ----
579: ----
580: ----
581: ----
582: ----
583: ----
584: CCCXXIII = 323
585: ----
586: ----
587: ----
588: ----
589: ----
590: MMDCCCLXXXI = 2881
591: MCMXLII = 1942
592: MDCLXIX = 1669
593: ----
594: ----
595: ----
596: ----
597: ----
598: ----
599: ----
600: MDCCCIII = 1803
601: MMMCCLXIV = 3264
602: CDXVI = 416
603: ----
604: ----
605: ----
606: ----
607: ----
608: ----
609: MMDCCVIII = 2708
610: ----
611: ----
612: ----
613: ----
614: CLXXXVI = 186
615: ----
616: ----
617: DCCXCII = 792
618: ----
619: ----
620: ----
621: ----
622: MDCCCLXXXVII = 1887
623: MMMDCIII = 3603
624: DCCCV = 805
625: ----
626: ----
627: CCXLII = 242
628: ----
629: ----
630: ----
631: MMMLXXXIII = 3083
632: ----
633: ----
634: ----
635: ----
636: MMMDCCCLXVII = 3867
637: ----
638: MDCCCLI = 1851
639: ----
640: ----
641: ----
642: ----
643: ----
644: ----
645: ----
646: ----
647: ----
648: ----
649: ----
650: ----
651: ----
652: ----
653: ----
654: ----
655: ----
656: ----
657: ----
658: ----
659: ----
660: MDLXXXIII = 1583
661: DCXXV = 625
662: ----
663: MMCCLXXIII = 2273
664: ----
665: ----
666: ----
667: ----
668: ----
669: MDCLXXII = 1672
670: ----
671: ----
672: ----
673: MMLXXX = 2080
674: DCCXXII = 722
675: ----
676: MMMCCCLXX = 3370
677: XLIV = 44
678: ----
679: ----
680: ----
681: ----
682: ----
683: DCLXVIII = 668
684: ----
685: ----
686: ----
687: ----
688: ----
689: CDXXV = 425
690: ----
691: ----
692: ----
693: MMCVI = 2106
694: ----
695: ----
696: ----
697: CCCLXXXVIII = 388
698: MMMCDLXXXI = 3481
699: ----
700: CMXLVIII = 948
701: MMDCCLXIII = 2763
702: ----
703: ----
704: ----
705: ----
706: MCDLII = 1452
707: ----
708: MMMCCCXCIII = 3393
709: ----
710: CXCIII = 193
711: ----
712: ----
713: ----
714: MXCVIII = 1098
715: ----
716: MMDCCCXCII = 2892
717: ----
718: ----
719: ----
720: DCCLXXV = 775
721: ----
722: ----
723: ----
724: ----
725: ----
726: CCCLXXVIII = 378
727: ----
728: ----
729: ----
730: ----
731: ----
732: ----
733: ----
734: ----
735: ----
736: MMMCCXCVI = 3296
737: ----
738: ----
739: MMLXXVII = 2077
740: MMMDCCLXXVI = 3776
741: MMMDCCCLXXXI = 3881
742: MMMCCCXXVIII = 3328
743: MMMCDXXIV = 3424
744: CMLXII = 962
745: MCMLIII = 1953
746: MCMLXXXIII = 1983
747: LXXIX = 79
748: ----
749: ----
750: ----
751: ----
752: ----
753: ----
754: ----
755: ----
756: ----
757: ----
758: ----
759: ----
760: ----
761: ----
762: ----
763: MCXC = 1190
764: ----
765: ----
766: ----
767: ----
768: MDCXC = 1690
769: MMMCDLXXVII = 3477
770: MDCLVIII = 1658
771: ----
772: ----
773: ----
774: DCCVIII = 708
775: ----
776: ----
777: CDXXVII = 427
778: MMCMXCI = 2991
779: ----
780: MMMCCLXXIX = 3279
781: ----
782: ----
783: MMMDCCCLXXVII = 3877
784: MMDCCCLVI = 2856
785: DXL = 540
786: ----
787: ----
788: MCCLXXXV = 1285
789: ----
790: DCCC = 800
791: MMCMXCV = 2995
792: ----
793: ----
794: ----
795: ----
796: ----

HashQP #2 with String key has: 

0: ----
1: ----
2: ----
3: ----
4: ----
5: MMMDCCCXLIX = 3849
6: ----
7: ----
8: ----
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: MMDCCLXXII = 2772
17: ----
18: ----
19: ----
20: ----
21: MMCVIII = 2108
22: CCLVII = 257
23: ----
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: MXCVIII = 1098
32: ----
33: ----
34: ----
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: CMXXXV = 935
42: MMMCXCVIII = 3198
43: ----
44: MDCLXIX = 1669
45: ----
46: MCCCLXXXII = 1382
47: ----
48: ----
49: ----
50: ----
51: ----
52: ----
53: MCMV = 1905
54: ----
55: ----
56: ----
57: ----
58: ----
59: ----
60: ----
61: MCCCLXXXIX = 1389
62: ----
63: ----
64: ----
65: MDCLXXVI = 1676
66: MCCCVI = 1306
67: ----
68: MMCCCXXVIII = 2328
69: MMCCLXXIII = 2273
70: DCLXVI = 666
71: ----
72: ----
73: ----
74: ----
75: DCCXXII = 722
76: ----
77: ----
78: ----
79: ----
80: ----
81: ----
82: ----
83: MDCLX = 1660
84: MX = 1010
85: ----
86: ----
87: ----
88: MMCLXXXII = 2182
89: ----
90: ----
91: ----
92: ----
93: ----
94: ----
95: ----
96: ----
97: ----
98: ----
99: ----
100: ----
101: MMCLXXXIV = 2184
102: MMMCDXXIV = 3424
103: ----
104: ----
105: ----
106: ----
107: MMMCMIX = 3909
108: MMMCCXIX = 3219
109: ----
110: MMDCXXVIII = 2628
111: ----
112: LXXIX = 79
113: ----
114: ----
115: ----
116: ----
117: DCCLXXV = 775
118: ----
119: ----
120: MMMDCCXXXIV = 3734
121: ----
122: DCCCXIV = 814
123: ----
124: ----
125: ----
126: MCMLXXXIII = 1983
127: ----
128: MMDXC = 2590
129: ----
130: ----
131: ----
132: ----
133: ----
134: MMDCCXVII = 2717
135: ----
136: ----
137: MMMXIX = 3019
138: MMCCLXXXII = 2282
139: ----
140: ----
141: ----
142: MMCCLX = 2260
143: MMMDCCXXXIII = 3733
144: MCCCXLVII = 1347
145: ----
146: ----
147: ----
148: ----
149: ----
150: ----
151: MMMDCCCXLIII = 3843
152: ----
153: ----
154: ----
155: ----
156: MMLXXVII = 2077
157: ----
158: ----
159: ----
160: ----
161: ----
162: ----
163: ----
164: MMCDIII = 2403
165: ----
166: ----
167: ----
168: DLXXXIII = 583
169: ----
170: ----
171: MMDCCCXIII = 2813
172: ----
173: MMCMLI = 2951
174: MDCCCLXXXIII = 1883
175: ----
176: ----
177: ----
178: ----
179: ----
180: MMCCXXXI = 2231
181: ----
182: ----
183: ----
184: ----
185: ----
186: ----
187: CMXII = 912
188: MMCMLX = 2960
189: ----
190: ----
191: ----
192: ----
193: ----
194: DCCC = 800
195: ----
196: ----
197: ----
198: ----
199: DCCVIII = 708
200: MCCCXCI = 1391
201: ----
202: ----
203: ----
204: ----
205: ----
206: ----
207: ----
208: ----
209: MDCXCII = 1692
210: CDXLII = 442
211: ----
212: ----
213: ----
214: ----
215: ----
216: ----
217: MCMLIII = 1953
218: MMMLXXXIII = 3083
219: ----
220: MMDCXXXIX = 2639
221: ----
222: ----
223: ----
224: ----
225: ----
226: ----
227: ----
228: ----
229: ----
230: ----
231: ----
232: MMDCXXVI = 2626
233: CCLXXVII = 277
234: CXC = 190
235: ----
236: ----
237: ----
238: ----
239: MMXXV = 2025
240: ----
241: ----
242: ----
243: ----
244: ----
245: ----
246: ----
247: ----
248: ----
249: ----
250: ----
251: ----
252: ----
253: ----
254: ----
255: ----
256: ----
257: ----
258: ----
259: ----
260: ----
261: ----
262: ----
263: CDXVI = 416
264: MMDVII = 2507
265: ----
266: ----
267: ----
268: ----
269: ----
270: MMMCDLXXXI = 3481
271: ----
272: ----
273: ----
274: ----
275: ----
276: ----
277: ----
278: ----
279: ----
280: MMMCDLXXVII = 3477
281: ----
282: ----
283: ----
284: ----
285: ----
286: ----
287: ----
288: ----
289: MMMDCCCLV = 3855
290: MMCXII = 2112
291: ----
292: ----
293: MMMCMXLIII = 3943
294: CMXLV = 945
295: ----
296: ----
297: MMMLXXI = 3071
298: MCCCV = 1305
299: MCCCX = 1310
300: CCCVIII = 308
301: MMLXXX = 2080
302: ----
303: ----
304: MCCCLXVI = 1366
305: ----
306: XXII = 22
307: ----
308: ----
309: ----
310: DXXI = 521
311: ----
312: ----
313: ----
314: ----
315: ----
316: ----
317: ----
318: MCCLXVI = 1266
319: ----
320: ----
321: ----
322: ----
323: MLXXXII = 1082
324: MMCDLXI = 2461
325: ----
326: MMCDXLI = 2441
327: ----
328: MDCXCI = 1691
329: ----
330: ----
331: ----
332: ----
333: ----
334: DCCCXVII = 817
335: MMMCCLXXX = 3280
336: MDCCCLI = 1851
337: MMCDLXV = 2465
338: MDCCCXXXVI = 1836
339: CDXXV = 425
340: MMCDXLV = 2445
341: ----
342: ----
343: ----
344: ----
345: ----
346: ----
347: ----
348: MCMXLII = 1942
349: ----
350: MCXXXI = 1131
351: ----
352: ----
353: ----
354: ----
355: ----
356: ----
357: MMMDCCCLXXXI = 3881
358: DLXXXV = 585
359: ----
360: ----
361: MMMDCCCLXVII = 3867
362: MMMDCCCXCVII = 3897
363: MMMIX = 3009
364: ----
365: MCLIV = 1154
366: MCCCXLVI = 1346
367: ----
368: ----
369: ----
370: ----
371: ----
372: MCCXLIII = 1243
373: ----
374: MMCMXCI = 2991
375: MCXC = 1190
376: ----
377: ----
378: MMDCCLX = 2760
379: MCCCLXXV = 1375
380: ----
381: MCCCLXXX = 1380
382: ----
383: ----
384: ----
385: ----
386: ----
387: MMCMXCV = 2995
388: ----
389: CCL = 250
390: MXCIII = 1093
391: ----
392: MMMVIII = 3008
393: ----
394: MMMCDXII = 3412
395: ----
396: MMCXLV = 2145
397: ----
398: ----
399: ----
400: MDCCCXXX = 1830
401: MMMDCCCLXX = 3870
402: ----
403: CCCLXXXVIII = 388
404: ----
405: ----
406: ----
407: DXL = 540
408: MMMCLXX = 3170
409: ----
410: ----
411: MMCCXXX = 2230
412: CCXLII = 242
413: ----
414: ----
415: ----
416: ----
417: ----
418: ----
419: MMLXXXIII = 2083
420: MMDXCV = 2595
421: MMMCMXCIX = 3999
422: ----
423: ----
424: ----
425: ----
426: ----
427: ----
428: ----
429: ----
430: CCLXXXV = 285
431: MMMCCCLXX = 3370
432: MCI = 1101
433: CDX = 410
434: MDCXC = 1690
435: MMMCLXIX = 3169
436: ----
437: ----
438: MXXXIV = 1034
439: MMMCCXCVI = 3296
440: MMXV = 2015
441: ----
442: MMCDXXVII = 2427
443: ----
444: ----
445: MMMCCCXCIII = 3393
446: ----
447: MLXVIII = 1068
448: ----
449: ----
450: MMMCDLXII = 3462
451: DCCLXIX = 769
452: ----
453: MMCXCIX = 2199
454: ----
455: ----
456: ----
457: MMCDXCVI = 2496
458: ----
459: MDCLXXII = 1672
460: ----
461: ----
462: ----
463: ----
464: ----
465: ----
466: MDL = 1550
467: ----
468: ----
469: ----
470: MMMCCXXXIX = 3239
471: ----
472: ----
473: ----
474: ----
475: ----
476: MDV = 1505
477: ----
478: ----
479: MCMXCIV = 1994
480: MMCCLXVI = 2266
481: MMDIX = 2509
482: ----
483: MCCXXXVI = 1236
484: ----
485: ----
486: ----
487: ----
488: ----
489: ----
490: ----
491: MMMDCXCVIII = 3698
492: CCLXXIII = 273
493: MMMCCX = 3210
494: CXCIII = 193
495: MMMCCCXXXIX = 3339
496: ----
497: MCXXXVI = 1136
498: ----
499: ----
500: MMMCDLXX = 3470
501: MMMDCXCI = 3691
502: CDXXVII = 427
503: MMMCCLXXIX = 3279
504: ----
505: MMCCLXXXIII = 2283
506: ----
507: ----
508: ----
509: ----
510: ----
511: ----
512: ----
513: ----
514: ----
515: ----
516: ----
517: ----
518: ----
519: ----
520: ----
521: DCCCV = 805
522: ----
523: ----
524: ----
525: MMDCCCLVI = 2856
526: MMMCCXX = 3220
527: ----
528: ----
529: ----
530: ----
531: ----
532: DCXXVIII = 628
533: XXVII = 27
534: CMVI = 906
535: ----
536: ----
537: ----
538: ----
539: ----
540: ----
541: MMCCXLVI = 2246
542: DXLII = 542
543: MMMDCCLXXVI = 3776
544: MDCCCXCV = 1895
545: MMCCLVII = 2257
546: ----
547: ----
548: MMLIII = 2053
549: CCXI = 211
550: ----
551: CLXXXVI = 186
552: CCXL = 240
553: CXXXIX = 139
554: ----
555: ----
556: ----
557: MMDCCCLXXXI = 2881
558: MDXV = 1515
559: CL = 150
560: MMMCMXL = 3940
561: ----
562: MMMLXIII = 3063
563: MCXXVII = 1127
564: MCMXIX = 1919
565: CCXX = 220
566: ----
567: ----
568: ----
569: MMMDCCCLXXVII = 3877
570: ----
571: ----
572: ----
573: MMCMXIV = 2914
574: ----
575: ----
576: ----
577: ----
578: CCLXXXIII = 283
579: ----
580: ----
581: ----
582: ----
583: MDCLVIII = 1658
584: ----
585: ----
586: MMDCCVIII = 2708
587: CIX = 109
588: MDCCXXXVI = 1736
589: ----
590: ----
591: MCCLXXXV = 1285
592: ----
593: ----
594: ----
595: ----
596: ----
597: ----
598: ----
599: ----
600: ----
601: ----
602: ----
603: ----
604: MMDI = 2501
605: ----
606: ----
607: ----
608: ----
609: MMDCCXCVI = 2796
610: ----
611: ----
612: CCCXLIV = 344
613: MCCCXCVII = 1397
614: ----
615: CCCLXXVIII = 378
616: DCCXCII = 792
617: ----
618: MMMDCIII = 3603
619: ----
620: ----
621: MMMDCCCXCIII = 3893
622: MCMLV = 1955
623: ----
624: ----
625: ----
626: ----
627: ----
628: ----
629: ----
630: ----
631: DCXXV = 625
632: ----
633: ----
634: CCCXCI = 391
635: ----
636: ----
637: MMCCCXXIII = 2323
638: ----
639: MCMLXI = 1961
640: ----
641: ----
642: ----
643: ----
644: MCDLXXVIII = 1478
645: MCDLII = 1452
646: ----
647: ----
648: MDCCCIII = 1803
649: ----
650: ----
651: MMXLI = 2041
652: ----
653: ----
654: ----
655: ----
656: MMMDCCCXXXII = 3832
657: ----
658: ----
659: MLXVI = 1066
660: MMDCLVII = 2657
661: ----
662: ----
663: DCLXXIX = 679
664: ----
665: MMMCCCXXVIII = 3328
666: CMXLVIII = 948
667: ----
668: ----
669: MMMCCLXIV = 3264
670: ----
671: MMCCXXXIX = 2239
672: ----
673: MMDCCXIV = 2714
674: ----
675: ----
676: ----
677: ----
678: ----
679: MMMCCCLXVI = 3366
680: ----
681: ----
682: ----
683: ----
684: CCLXVIII = 268
685: ----
686: ----
687: ----
688: ----
689: ----
690: ----
691: ----
692: ----
693: ----
694: ----
695: ----
696: ----
697: ----
698: ----
699: ----
700: ----
701: ----
702: ----
703: ----
704: ----
705: MMCVI = 2106
706: ----
707: MXXIX = 1029
708: ----
709: CCCXXIII = 323
710: XXVI = 26
711: MDCCCXL = 1840
712: MDCCCLXXXVII = 1887
713: MMCDXXVI = 2426
714: ----
715: MDXCVII = 1597
716: ----
717: ----
718: MMDCCCXXXV = 2835
719: ----
720: MCLXXI = 1171
721: CMLXII = 962
722: ----
723: ----
724: ----
725: ----
726: ----
727: MCDLI = 1451
728: CDXX = 420
729: MCIX = 1109
730: ----
731: ----
732: ----
733: ----
734: ----
735: MMCDXCIII = 2493
736: ----
737: ----
738: ----
739: ----
740: ----
741: ----
742: XLIV = 44
743: ----
744: MMDCCLXIII = 2763
745: MDCCCXXXIV = 1834
746: ----
747: ----
748: MDLXXXIII = 1583
749: DIV = 504
750: ----
751: ----
752: CMLXXXIV = 984
753: ----
754: MMMCCCLXXV = 3375
755: ----
756: ----
757: ----
758: DCLXVIII = 668
759: ----
760: ----
761: CDXXIII = 423
762: MMMCCCLIV = 3354
763: ----
764: CMLXVII = 967
765: CMXCVII = 997
766: ----
767: ----
768: MMCCXXXVIII = 2238
769: ----
770: MMCDXXXII = 2432
771: ----
772: ----
773: ----
774: ----
775: ----
776: ----
777: ----
778: ----
779: ----
780: ----
781: MMDCCCXCII = 2892
782: ----
783: ----
784: ----
785: ----
786: ----
787: ----
788: ----
789: ----
790: ----
791: ----
792: ----
793: ----
794: ----
795: ----
796: ----

HashSC statistics:

In the HashSC class:

Table Size = 197
Number of entries = 243
Load factor = 1.233502538071066
Number of collisions = 129
Longest Linked List = 4

HashQP #1 statistics:

In the HashQP class:

Table Size = 797
Number of entries = 243
Load factor = 0.30489335006273527
Number of collisions = 65
Longest Collision Run = 4

HashQP #2 statistics:

In the HashQP class:

Table Size = 797
Number of entries = 243
Load factor = 0.30489335006273527
Number of collisions = 44
Longest Collision Run = 3

Testing HashSC:

The return value of calling getEntry for MMMMI = 4001 is null
The return value of calling remove for MMMMI = 4001 is false
The return value of calling insert for MMCMXCI = 2991 is false

Testing HashQP #1:

The return value of calling getEntry for MMMMI = 4001 is null
The return value of calling remove for MMMMI = 4001 is false
The return value of calling insert for MMCMXCI = 2991 is false

Calling getEntry in HashSC for MMCMXCI = 2991
Retrieved in HashSC, RomanNumeral: MMCMXCI = 2991, now trying to delete it
Calling remove for MMCMXCI = 2991 from HashSC returns true
Calling remove for MMCMXCI = 2991 from HashSC AGAIN returns false

Calling getEntry in HashQP for MMCMXCI = 2991
Retrieved in HashQP, RomanNumeral: MMCMXCI = 2991, now trying to delete it
Calling remove for MMCMXCI = 2991 from HashQP returns true
Calling remove for MMCMXCI = 2991 from HashQP AGAIN returns false

Now the statistics for HashSC are

In the HashSC class:

Table Size = 197
Number of entries = 242
Load factor = 1.2284263959390862
Number of collisions = 133
Longest Linked List = 4

Now the statistics for one of the HashQPs are

In the HashQP class:

Table Size = 797
Number of entries = 242
Load factor = 0.30363864491844417
Number of collisions = 65
Longest Collision Run = 4

 */
}
