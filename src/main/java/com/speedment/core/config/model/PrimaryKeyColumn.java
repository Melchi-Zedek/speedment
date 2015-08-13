/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.core.config.model;

import com.speedment.core.config.model.aspects.Ordinable;
import com.speedment.core.annotations.Api;
import com.speedment.core.config.model.aspects.Child;
import com.speedment.core.config.model.aspects.Columnable;
import com.speedment.core.config.model.aspects.Enableable;
import com.speedment.core.config.model.aspects.Node;
import com.speedment.core.config.model.impl.PrimaryKeyColumnImpl;
import java.util.function.Supplier;

/**
 *
 * @author pemi
 */
@Api(version = "2.0")
public interface PrimaryKeyColumn extends Node, Enableable, Ordinable, 
    Columnable, Child<Table> {

    /**
     * Factory holder.
     */
    enum Holder { HOLDER;
        private Supplier<PrimaryKeyColumn> provider = PrimaryKeyColumnImpl::new;
    }

    /**
     * Sets the instantiation method used to create new instances of this
     * interface.
     * 
     * @param provider  the new constructor 
     */
    static void setSupplier(Supplier<PrimaryKeyColumn> provider) {
        Holder.HOLDER.provider = provider;
    }

    /**
     * Creates a new instance implementing this interface by using the class
     * supplied by the default factory. To change implementation, please use
     * the {@link #setSupplier(java.util.function.Supplier) setSupplier} method.

     * @return  the new instance
     */
    static PrimaryKeyColumn newPrimaryKeyColumn() {
        return Holder.HOLDER.provider.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default Class<PrimaryKeyColumn> getInterfaceMainClass() {
        return PrimaryKeyColumn.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default Class<Table> getParentInterfaceMainClass() {
        return Table.class;
    }
}