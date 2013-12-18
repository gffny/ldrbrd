/**
 * 
 */
package com.gffny.ldrbrd.web.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.IteratorUtils;

import com.gffny.ldrbrd.web.model.JSONable;

/**
 * @author jdgaffney
 * 
 *         Collection of typed JSONable response entities
 */
public class JsonCollection<RESPONSE_ENTITY extends JSONable> extends
                ArrayList<RESPONSE_ENTITY> implements JSONable {

        /**
   * 
   */
        private static final long serialVersionUID = 1L;

        /**
         * @param items
         *            the items to set
         */
        public void setItems(Collection<RESPONSE_ENTITY> items) {
                this.addAll(items);
        }

        public JsonCollection(Collection<RESPONSE_ENTITY> items) {
                setItems(items);
        }

        @SuppressWarnings("unchecked")
        public JsonCollection(Iterable<RESPONSE_ENTITY> items) {
                Collection<RESPONSE_ENTITY> itemCollection = IteratorUtils.toList(items
                                .iterator());
                setItems(itemCollection);
        }

}