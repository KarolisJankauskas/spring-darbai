import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { MainLayout } from "./layouts/MainLayout.jsx";
import { AdList } from "./pages/ads/AdList.jsx";
import { AuthGuard } from "./components/AuthGuard.jsx";
import { Login } from "./pages/auth/Login.jsx";
import { Register } from "./pages/auth/Register.jsx";
import { AuthProvider } from "./context/AuthContext.jsx";
import { Home } from "./pages/Home.jsx";
import { About } from "./pages/About.jsx";
import { Contact } from "./pages/Contact.jsx";
import { Navbar } from "./pages/ads/NavBar.jsx";
import { AddAdPage } from "./pages/ads/AddAd.jsx";
// import { Footer } from "./pages/Footer.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <AuthProvider>
                {/* Navbar is placed outside Routes to appear on all pages */}
                <Navbar />
                <Routes>
                    {/* Public Routes */}
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />

                    {/* Protected Routes (wrapped in AuthGuard and MainLayout) */}
                    <Route
                        path="/"
                        element={
                            <AuthGuard>
                                <MainLayout />
                            </AuthGuard>
                        }
                    >
                        <Route index element={<Navigate to="home" replace />} />
                        <Route path="home" element={<Home />} />
                        <Route path="ads" element={<AdList />} />
                        <Route path="about" element={<About />} />
                        <Route path="contact" element={<Contact />} />
                        <Route
                    path="/admin/add-ad"
                    element={
                        <AuthGuard>
                            <AddAdPage />
                        </AuthGuard>
                    }
                />
                       
                    </Route>

                    <Route path="*" element={<Navigate to="/home" replace />} />
                </Routes>
            </AuthProvider>
        </BrowserRouter>
    );
};

export default App;