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

import com.speedment.core.annotations.Api;
import com.speedment.core.config.model.aspects.Parent;
import com.speedment.core.config.model.aspects.Child;
import com.speedment.core.config.model.aspects.Enableable;
import com.speedment.core.config.model.aspects.Node;
import com.speedment.core.config.model.impl.DbmsImpl;
import com.speedment.core.config.model.parameters.DbmsTypeable;
import java.util.Optional;
import java.util.function.Supplier;

/**
 *
 * @author pemi
 */
@Api(version = "2.0")
public interface Dbms extends Node, Enableable, DbmsTypeable, Child<Project>, Parent<Schema> {

    /**
     * Factory holder.
     */
    enum Holder { HOLDER;
        private Supplier<Dbms> provider = DbmsImpl::new;
    }

    /**
     * Sets the instantiation method used to create new instances of this
     * interface.
     * 
     * @param provider  the new constructor 
     */
    static void setSupplier(Supplier<Dbms> provider) {
        Holder.HOLDER.provider = provider;
    }

    /**
     * Creates a new instance implementing this interface by using the class
     * supplied by the default factory. To change implementation, please use
     * the {@link #setSupplier(java.util.function.Supplier) setSupplier} method.

     * @return  the new instance
     */
    static Dbms newDbms() {
        return Holder.HOLDER.provider.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default Class<Dbms> getInterfaceMainClass() {
        return Dbms.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default Class<Project> getParentInterfaceMainClass() {
        return Project.class;
    }

    /**
     * Creates and adds a new {@link Schema} as a child to this node in the 
     * configuration tree.
     * 
     * @return  the newly added child
     */
    default Schema addNewSchema() {
        final Schema e = Schema.newSchema();
        add(e);
        return e;
    }

    /**
     * Returns the address of the database host if it is specified. The address
     * could be an ip-address or a hostname. If no address is specified, 
     * {@code empty} will be returned.
     * <p>
     * This property is editable in the GUI through reflection.
     * 
     * @return  the address of the host or {@code empty}
     */
    @External(type = String.class)
    Optional<String> getIpAddress();

    /**
     * Sets the address of the database host. The address could be an ip-address 
     * or a hostname.
     * <p>
     * This property is editable in the GUI through reflection.
     * 
     * @param ipAddress  the new address of the host or {@code null}
     */
    @External(type = String.class)
    void setIpAddress(String ipAddress);

    /**
     * Returns the port number of the database on the database host. If no port
     * is specified, {@code empty} is returned.
     * <p>
     * This property is editable in the GUI through reflection.
     * 
     * @return  the port of the database or {@code empty}
     */
    @External(type = Integer.class)
    Optional<Integer> getPort();

    /**
     * Sets the port number of the database on the database host. If no port
     * should be specified, enter {@code null}.
     * <p>
     * This property is editable in the GUI through reflection.
     * 
     * @param port  the port of the database or {@code null}
     */
    @External(type = Integer.class)
    void setPort(Integer port);

    /**
     * Returns the database username to use when connecting to the dbms. If no 
     * username is specified, {@code empty} is returned.
     * <p>
     * This property is editable in the GUI through reflection.
     * 
     * @return  the database username or {@code empty}
     */
    @External(type = String.class)
    Optional<String> getUsername();

    /**
     * Sets the database username to use when connecting to the dbms. If no 
     * username should be specified, use {@code null}.
     * <p>
     * This property is editable in the GUI through reflection.
     * 
     * @param username  the database username or {@code null}
     */
    @External(type = String.class)
    void setUsername(String username);

    /**
     * Returns the password to use when connecting to the dbms. If no 
     * password is specified, {@code empty} is returned.
     * <p>
     * This property is editable in the GUI through reflection, but will not
     * appear in generated configuration files for security reasons.
     * 
     * @return  the dbms password or {@code empty}
     */
    @External(type = String.class, isSecret = true)
    Optional<String> getPassword();

    /**
     * Sets the password to use when connecting to the dbms. If no password 
     * should be specified, use {@code null} instead.
     * <p>
     * This property is editable in the GUI through reflection, but will not
     * appear in generated configuration files for security reasons.
     * 
     * @param password  the dbms password or {@code null}
     */
    @External(type = String.class, isSecret = true)
    void setPassword(String password);
}