import { Box } from "@mui/material";
import React from "react";
import Header from "../header/header.page";
import Footer from "../Footer/footer.page";
import { PageLayoutProps } from "../../interfaces/page-layout";
import { FOOTER_HEIGHT, HEADER_HEIGHT } from "../../constants/layout.constants";

const PageLayout: React.FC<PageLayoutProps> = ({ children, header, footer, centerContent = false, }) => {
    return (
        <Box display="flex" flexDirection="column" minHeight="100vh">
            {header ?? <Header />}
            <Box
                display="flex"
                justifyContent={centerContent ? "center" : "flex-start"}
                alignItems={centerContent ? "center" : "flex-start"}
                sx={
                    centerContent
                        ? { height: `calc(100vh - ${HEADER_HEIGHT}px - ${FOOTER_HEIGHT}px)` }
                        : { flex: 1 }
                }
            >
                {children}
            </Box>
            {footer ?? <Footer />}
        </Box >
    );
};

export default PageLayout;
