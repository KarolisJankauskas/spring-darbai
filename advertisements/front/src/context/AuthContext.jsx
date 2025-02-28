import { createContext, useContext, useState } from "react";
import { useNavigate } from "react-router";
import api, { setAuth, clearAuth } from "../utils/api.js";

const AuthContext = createContext({
    user: null,
    login: () => {},
    logout: () => {},
    register: () => {},
});

export const AuthProvider = ({ children }) => {
    const navigate = useNavigate();
    const [user, setUser] = useState(() => {
        const maybeUser = localStorage.getItem("user");
        return maybeUser ? JSON.parse(maybeUser) : null;
    });

    const login = async (username, password) => {
        try {
           
            setAuth(username, password);

        //  Gauti roles is serverio
            const response = await api.get("/auth/me");
            const userData = response.data;

         
            const user = {
                username,
                email: userData.email, // Add email from response
                roles: userData.roles,
            };
// Issaugo i localstorage
            localStorage.setItem("user", JSON.stringify(user));
            setUser(user);
            navigate("/movies");
        } catch (error) {
            console.error("Login failed:", error);
            throw error;
        }
    };

    const register = async (username, email, password) => {
        try {
            await api.post("/auth/register", {
                username,
                email,
                password,
            });
            navigate("/login");
        } catch (error) {
            console.error("Registration failed:", error);
            throw error;
        }
    };

    const logout = () => {
        setUser(null);
        clearAuth(); 
        localStorage.removeItem("user");
        navigate("/login"); 
    };

    return (
        <AuthContext.Provider value={{ user, login, logout, register }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
};